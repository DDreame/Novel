package novel.storage.impl;

import novel.spider.entity.Novel;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.storage.Processor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Classname AbstractNovelStorage
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/18 23:42
 * @Version 1.0
 **/
public abstract class AbstractNovelStorage implements Processor {
    protected Map<String,String> tasks = new TreeMap<>();
    protected  SqlSessionFactory sqlSessionFactory;
    public AbstractNovelStorage() throws Exception{
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("SqlMapConfig.xml"));
    }

    @Override
    public void process(){
        final ExecutorService service = Executors.newFixedThreadPool((tasks.size()));
        List<Future<String>> futures = new ArrayList<>(tasks.size());
        for(Map.Entry<String,String> entry:tasks.entrySet()){
            String key = entry.getKey();
            final String value = entry.getValue();
            futures.add(service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    INovelSpider spider = NovelSpiderFactory.getNovelSpider(value);
                    Iterator<List<Novel>> iterator = spider.iterator(value, 10);
                    while(iterator.hasNext()){
                        System.err.println("开始抓取[" + key + "] 的 URL:" + spider.next());
                        List<Novel> novels = iterator.next();
                        for (Novel novel : novels) {
                            //设置小说的名字的首字母
                            novel.setFirstLetter(key.charAt(0));
                        }
                        SqlSession session = sqlSessionFactory.openSession();
                        session.insert("batchInsert",novels);
                        session.commit(true);
                        session.close();
                        Thread.sleep(1_000);
                    }
                    return key;
                }
            }));
        }
        service.shutdown();
        for(Future<String> future: futures){
            try{
                System.out.println("抓取["+future.get()+"]结束！");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

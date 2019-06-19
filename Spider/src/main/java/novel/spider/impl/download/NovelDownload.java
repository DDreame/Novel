package novel.spider.impl.download;


import novel.spider.NovelSiteEnum;
import novel.spider.configuration.Configuration;
import novel.spider.entity.Chapter;
import novel.spider.entity.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.util.ChapterDetailSpiderFactory;
import novel.spider.util.ChapterSpiderFactory;
import novel.spider.util.NovelSpiderUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;


/**
 * @Classname NovelDownload
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/9 22:21
 * @Version 1.0
 **/
public class NovelDownload implements INovelDownload {

    @Override
    public String download(String url, Configuration config) {
        IChapterSpider spider = ChapterSpiderFactory.getChapterDetailSpider(url);
        List<Chapter> chapters = spider.getsChapter(url);
        //某个线程下载完成后通知主线程下载完毕。全部下载完成后进行合并。
        int size = config.getSize();
        //Math.ceil(double) 10 -> 10 10.5 -> 11 -10 -> -10 -10.5 -> -10
        int maxThreadSize = (int)Math.ceil( chapters.size() * 1.0 /size);
        Map<String,List<Chapter>> downloadTaskAlloc = new HashMap<>();
        for(int i = 0; i < maxThreadSize; i++){
            //i = 0 0-100
            //i = 1 1-199
            //······
            int fromIndex = i*(config.getSize());

            int toIndex = i == maxThreadSize - 1 ? chapters.size() : i * (config.getSize()) + config.getSize();
            downloadTaskAlloc.put(fromIndex+"-"+toIndex,chapters.subList(fromIndex,toIndex));
        }
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<>();
        //通过以下两行代码创建路径
        String savePath = config.getPath()+"/"+ NovelSiteEnum.getEnumByUrl(url).getUrl();
        new File(savePath).mkdirs();
        for(String key : keySet){
            tasks.add(service.submit(new DownLoadCallable(savePath+"/"+key+".txt",downloadTaskAlloc.get(key),config.getTryTimes())));
        }
        service.shutdown();
        for(Future<String> future:tasks){
                try {
                    System.out.println(future.get()+",下载完成！");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
        }
        NovelSpiderUtil.multiFileMerge(savePath,null,true);
        return savePath+"/merge.txt";
    }
}

class DownLoadCallable implements Callable<String>{
    private List<Chapter> chapters;
    private String path;
    private int tryTimes;
    public DownLoadCallable(String path,List<Chapter> chapters,int tryTimes){
        this.chapters = chapters;
        this.path = path;
        this.tryTimes = tryTimes;
    }
    @Override
    public String call() {
        try(
                PrintWriter out = new PrintWriter(new File(path),"UTF-8")
                ){
            for(Chapter chapter :chapters){
                IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChaptgerDetailSpider(chapter.getUrl());
                ChapterDetail chapterDetail = null;
                for(int i = 0;i < tryTimes; i++){
                    try{
                       chapterDetail =  spider.getChapterDetail(chapter.getUrl());
                       break;
                    }catch (RuntimeException e){
                        System.out.println("尝试第"+(i+1)+"/"+tryTimes+"次失败！");
                    }
                }
                out.println(chapterDetail.getTitle());
                out.println(chapterDetail.getContent());
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return path;
    }
}

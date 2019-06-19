import novel.spider.NovelSiteEnum;
import novel.spider.configuration.Configuration;
import novel.spider.entity.Chapter;
import novel.spider.entity.Novel;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.impl.download.NovelDownload;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownload;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @Classname TestCase
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/5 19:48
 * @Version 1.0
 **/
public class TestCase {

    @Test
    public void testGetChapter() throws Exception{
        IChapterSpider spider = new DefaultChapterSpider();
        List<Chapter> chapterList = spider.getsChapter("https://www.bixia.org/224_224644/");
        for(Chapter chapter : chapterList){
            System.out.println(chapter);
        }
    }

    @Test
    public void testGetBxChapter() throws Exception{
        IChapterSpider spider = new BxwxChapterSpider();
        List<Chapter> chapterList = spider.getsChapter("https://www.bxwx9.org/b/56/56357/");
        for(Chapter chapter : chapterList){
            System.out.println(chapter);
        }
    }

    @Test
    public void testGetSiteContext(){
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("https://www.booktxt.net/7_7402/3477656.html")));
    }

    @Test
    public void tesetGetChapterDetil(){
        IChapterDetailSpider detailSpider = new DefaultChapterDetailSpider();
            System.out.println(detailSpider.getChapterDetail("https://www.biquge.biz/21_21974/8978506.html").getContent());
    }

    @Test
    public void testDownload(){
        INovelDownload download = new NovelDownload();
        Configuration config = new Configuration();
        config.setPath("D:\\1");
        config.setSize(50);
        download.download("https://www.biquge.biz/22_22083/",config);
    }

    @Test
    public void testMultiFilemerge(){

        NovelSpiderUtil.multiFileMerge("D:/1",null,true);
    }

    @Test
    public void testBxwxNovelSpider(){
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.bxwx9.org/modules/article/index.php");
        List<Novel> Novels = spider.getNovel("https://www.bxwx9.org/modules/article/index.php",3);
        for(Novel novel : Novels){
            System.out.println(novel);
        }
    }

    @Test
    public void testBxwxNovelIterator(){
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.bxwx9.org/modules/article/index.php");
   //     List<Novel> novel = spider.getNovel("https://www.bxwx9.org/modules/article/index.php");
        Iterator<List<Novel>> novelIterator = spider.iterator("https://www.bxwx9.org/binitialE/0/1.htm",3);
        while (novelIterator.hasNext()){
            System.out.println("Url:"+spider.next());
            List<Novel> novels = novelIterator.next();
//            for(Novel novel:novels){
//                System.out.println(novel);
//            }
        }
    }
}

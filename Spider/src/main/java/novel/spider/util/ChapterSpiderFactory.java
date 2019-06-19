package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.BxwxChapterSpider;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.interfaces.IChapterSpider;

/**
 * @Classname ChapterSpiderFactory
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/9 22:26
 * @Version 1.0
 **/
public final class ChapterSpiderFactory {
    private ChapterSpiderFactory(){}

    /**
     * 通过给定的一个url，返回一个实现了IchapterSpider接口的实现类
     * @param url
     * @return
     */
    public static IChapterSpider getChapterDetailSpider(String url){
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum){
            case BiQuGe:
            case BiXia:
            case DingDianXiaoShuo:
                chapterSpider = new DefaultChapterSpider(); break;
            case BiXiaWenXue:
                chapterSpider = new BxwxChapterSpider(); break;
            default: break;
        }
        return chapterSpider;
    }


}

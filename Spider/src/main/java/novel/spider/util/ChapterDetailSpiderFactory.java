package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.chapter.DefaultChapterDetailSpider;
import novel.spider.interfaces.IChapterDetailSpider;

/**
 * @Classname ChapterDetailSpiderFactory
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/9 22:30
 * @Version 1.0
 **/
public final class ChapterDetailSpiderFactory {
    private ChapterDetailSpiderFactory(){}

    /**
     * 通过给定的url拿到实现了IchapterDetailSpider的具体实现类
     * @param url
     * @return
     */
    public static IChapterDetailSpider getChaptgerDetailSpider(String url){
        IChapterDetailSpider spider = null;
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum){
            case DingDianXiaoShuo:
            case BiXiaWenXue:
            case BiQuGe:
                spider  = new DefaultChapterDetailSpider();
            default: break;
        }
        return spider;

    }
}

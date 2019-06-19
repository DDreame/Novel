package novel.spider.util;

import novel.spider.NovelSiteEnum;
import novel.spider.impl.novel.BxwxNovelSpider;
import novel.spider.interfaces.INovelSpider;

/**
 * 生成书籍列表的实现类
 * @Classname NovelSpiderFactory
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/12 23:36
 * @Version 1.0
 **/
public final class NovelSpiderFactory {
    private NovelSpiderFactory(){}

    public static INovelSpider getNovelSpider(String url){
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum){
            case BiXiaWenXue: return new BxwxNovelSpider();
            default: throw new RuntimeException(url+"暂时不被支持");
        }
    }
}

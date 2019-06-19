package novel.spider.interfaces;


import novel.spider.entity.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * @InterfaceName INovelSpider
 * @Description TODO 爬取小说站点的书单列表
 * @Author XinChen
 * @Date 2019/6/11 19:29
 * @Version 1.0
 **/
public interface INovelSpider {
    /**  下载抓取某一个页面时最大的尝试次数*/
    public static final int MAX_TRY_TIMES = 3;

    /**
     * 通过url抓取小说列表实体
     * @param url
     * @param maxTryTimes 网页下载的最大次数（允许失败重下的次数）
     * @return
     */
    public List<Novel> getNovel(String url, Integer maxTryTimes);

    /**
     *
     * @return
     */
    public boolean hasNext();

    /**
     *
     * @return
     */
    public String next();

    /**
     *
     * @param firstpage
     * @return
     */
    public Iterator<List<Novel>> iterator(String firstpage, Integer maxTryTimes);

}

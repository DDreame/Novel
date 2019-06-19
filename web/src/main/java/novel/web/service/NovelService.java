package novel.web.service;

import novel.spider.entity.Novel;

import java.util.List;

/**
 * @InterfaceName NovelService
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/19 2:48
 * @Version 1.0
 **/
public interface NovelService {
    /**
     * 通过关键词查询数据库中的内容。
     * @param keyword
     * @return
     */
    List<Novel> getsNovelByKeyword(String keyword);

    /**
     * 查找对应平台下的小说
     * @param keyword
     * @param platformId
     * @return
     */
    List<Novel> getsNovelByKeyword(String keyword, int platformId);

}

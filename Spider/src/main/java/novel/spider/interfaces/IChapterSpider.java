package novel.spider.interfaces;

import novel.spider.entity.Chapter;

import java.util.List;

/**
 * 通过Url 获取完整的章节列表
 */
public interface IChapterSpider {
    public List<Chapter> getsChapter(String url);
}

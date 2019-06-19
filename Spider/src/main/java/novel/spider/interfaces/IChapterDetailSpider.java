package novel.spider.interfaces;

import novel.spider.entity.ChapterDetail;

public interface IChapterDetailSpider {
    /**
     * 通过给定的url返回对应的章节内容实体---标题，正文等
     * @param url
     * @return
     */
    public ChapterDetail getChapterDetail(String url);
}

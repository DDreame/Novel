package novel.spider.impl.chapter;

import novel.spider.NovelSiteEnum;
import novel.spider.entity.Chapter;
import novel.spider.impl.AbstrctSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname AbstractChapterSpider
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/5 19:38
 * @Version 1.0
 **/
public class AbstractChapterSpider extends AbstrctSpider implements IChapterSpider {
    @Override
    public List<Chapter> getsChapter(String url) {
        try{
            String result = crawl(url);
            Document doc = Jsoup.parse(result);
            doc.setBaseUri(url);
            Elements as = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
            List<Chapter> chapters = new ArrayList<>();
            for(Element a : as){
                Chapter chapter = new Chapter();
                chapter.setTitle(a.text());
                chapter.setUrl(a.absUrl("href"));
                chapters.add(chapter);
            }
            return chapters;
        }catch (Exception e){
            throw  new RuntimeException();
        }

    }
}

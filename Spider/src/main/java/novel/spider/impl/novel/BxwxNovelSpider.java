package novel.spider.impl.novel;

import novel.spider.NovelSiteEnum;
import novel.spider.entity.Novel;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 笔下文学网站的书籍列表爬取
 * @Classname BxwxNovelSpider
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/11 19:55
 * @Version 1.0
 **/
public class BxwxNovelSpider extends  AbstractNovelSpider {
    public BxwxNovelSpider() {
    }

    @Override
    public List<Novel> getNovel(String url,Integer maxTryTimes) {
        List<Novel> novels = new ArrayList<>();
        try {
            Elements trs = super.getTr(url, 2);
            for (int index = 1, size = trs.size(); index < trs.size(); index++) {
                Element tr = trs.get(index);
                Elements tds = tr.getElementsByTag("td");
                Novel novel = new Novel();
                novel.setName(tds.first().text());
                String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href").replace("/binfo", "/b").replace(".htm", "/index.html");
                novel.setUrl(novelUrl);
                novel.setLastUpdateChapter(tds.get(1).text().replace(":","-"));
                novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                novel.setAuthor(tds.get(2).text());
                novel.setLastUpdateTime(NovelSpiderUtil.getData(tds.get(4).text(), "yy-mm-dd"));
                novel.setStatus(NovelSpiderUtil.getNoveStatus(tds.get(5).text()));
                novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
                novels.add(novel);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return novels;
    }






}

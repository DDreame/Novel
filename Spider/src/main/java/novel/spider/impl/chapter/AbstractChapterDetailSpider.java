package novel.spider.impl.chapter;

import novel.spider.NovelSiteEnum;
import novel.spider.entity.ChapterDetail;
import novel.spider.impl.AbstrctSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * @Classname AbstractChapterDetailSpider
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/6 23:36
 * @Version 1.0
 **/
public class AbstractChapterDetailSpider extends AbstrctSpider implements IChapterDetailSpider {

    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crawl(url);
            result = result.replace("&npsp","${line}").replace("<br />","${line}").replace("<br/>","${line}");
            Document doc = Jsoup.parse(result);
            doc.setBaseUri(url);
            Map<String,String>  contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));

            //标题获取
            String titleSelector = contexts.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = parserSelector(splits);
            ChapterDetail detail = new ChapterDetail();
            detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //章节内容获取
            String contextSelector = contexts.get("chapter-detail-content-selector");
            detail.setContent(doc.select(contextSelector).first().text().replace("${line}","\n"));

            //前一章节url获取
            String prevSelector = contexts.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = parserSelector(splits);
            detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            //后一章节url获取
            String nextSelector = contexts.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = parserSelector(splits);
            detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            return detail;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 切割SelectorRule
     * @param splits
     * @return
     */

    protected String[] parserSelector(String[] splits){
        String[] newSplits = new String[2];
        if(splits.length == 1 ){
            newSplits[0] = splits[0];
            newSplits[1] = "0";
            return  newSplits;
        }else {
            return splits;
        }
    }


}

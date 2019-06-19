package novel.spider.impl.novel;

import novel.spider.NovelSiteEnum;
import novel.spider.entity.Novel;
import novel.spider.impl.AbstrctSpider;
import novel.spider.interfaces.INovelSpider;
import novel.spider.util.NovelSpiderUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *一个抽象的小说列表抓取，已经实现了解析tr元素的抓取。
 * @Classname AbstractNovelSpider
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/11 19:42
 * @Version 1.0
 **/
public abstract class AbstractNovelSpider extends AbstrctSpider implements INovelSpider {
    protected  Element nextPageElement;
    /**
     * 下一页的url
     */
    protected String nextPage;
    /**
     * 默认的抓取方法，最多会尝试INovelSpider.MAX_TRY_TIMES次
     * @param url
     * @return Elements
     * @throws Exception
     */
    protected Elements getTr(String url) throws Exception{
        return getTr(url,INovelSpider.MAX_TRY_TIMES);
    }


    /**
     * 以指定次数的形式进行解析指定网页
     * @param url
     * @param maxTryTimes 如果为null，则默认是INovelSpider.MAX_TRY_TIMES次
     * @return Elements
     * @throws Exception
     */
    protected Elements getTr(String url,Integer maxTryTimes) throws Exception{
        maxTryTimes = maxTryTimes == null ? INovelSpider.MAX_TRY_TIMES:maxTryTimes;
        Elements trs = null;
        for(int i  = 0; i <maxTryTimes; i++){
            try{
                String result = super.crawl(url);
                Map<String,String> context =NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
                String novelSelector = context.get("novel-selector");
                if(novelSelector  == null) {
                    throw new RuntimeException("url:"+url+"是不被支持抓取小说列表的网站");
                }
                Document doc = Jsoup.parse(result);
                doc.setBaseUri(url);
                trs = doc.select(novelSelector);
                String nextPageSelector = context.get("novel-nextPage_selector");
                if(nextPageSelector != null){
                    Elements nextPageElements = doc.select(nextPageSelector);
                    nextPageElement = nextPageElements == null ? null :nextPageElements.first();
                    /**
                     * 开始判断是否有下一页
                     */
                    if (nextPageElement != null) {
                        nextPage = nextPageElement.absUrl("href");
                        if(nextPage.equals(url)){
                            nextPage = "";
                        }
                    } else {
                        nextPage = "";
                    }
                }
                return trs;
            }catch (Exception e){

            }

        }
        throw new RuntimeException(url+"尝试了"+maxTryTimes+"次依然失败");
    }

    @Override
    public boolean hasNext() {
        return !nextPage.isEmpty();
    }

    @Override
    public String next() {

        return nextPage;
    }
    @Override
    public Iterator<List<Novel>> iterator(String firstpage,Integer maxTryTimes) {
        nextPage = firstpage;
        return new NovelIterator(maxTryTimes);
    }

    /**
     * 一个专门用于对小说网站书籍列表抓取的迭代器。
     */
    private class NovelIterator implements Iterator<List<Novel>> {
        private Integer maxTryTimes;
        protected NovelIterator(Integer maxTryTimes){
            this.maxTryTimes = maxTryTimes;
        }
        @Override
        public boolean hasNext() {
            return AbstractNovelSpider.this.hasNext();
        }
        @Override
        public List<Novel> next() {
            List<Novel> novels = getNovel(nextPage,maxTryTimes);
            return novels;
        }
        @Override
        public void remove() {
        }
    }
}

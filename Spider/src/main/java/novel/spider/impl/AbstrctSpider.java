package novel.spider.impl;

import novel.spider.NovelSiteEnum;
import novel.spider.util.NovelSpiderHttpGet;
import novel.spider.util.NovelSpiderUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @Classname AbstrctSpider
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/6 23:38
 * @Version 1.0
 **/
public class AbstrctSpider {
    /**
     * 抓取指定小说的内容
     * @param url
     * @return
     * @throws Exception
     */
    protected String crawl(String url) throws Exception{
        //try with resource 自动使用.close方法
        //谁最后被调用，谁最先被关闭
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))){
            String result = EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
            return result;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package novel.spider.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * @Classname NovelSpiderHttpGet
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/4 16:33
 * @Version 1.0
 **/
public class NovelSpiderHttpGet extends HttpGet {

    public NovelSpiderHttpGet(){

    }
    public NovelSpiderHttpGet(URI uri){
        super(uri);
    }

    public NovelSpiderHttpGet(String uri){
        super(uri);
    }

    /**
     * 设置默认的请求参数
     */
    private void setDefaultConfig(){
        this.setConfig(RequestConfig.custom()
                //设置链接服务器超时时间
                .setConnectTimeout(10_000)
                //设置从服务器读取数据的超时时间
                .setConnectionRequestTimeout(10_000)
                .build());
        //设置请求头
      //  this.setHeader("User-Agent","NovelSpider");

        //System.out.println(this.getConfig().getConnectionRequestTimeout());
    }
}

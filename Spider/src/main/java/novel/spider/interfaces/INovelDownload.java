package novel.spider.interfaces;


import novel.spider.configuration.Configuration;

/**
 * @InterfaceName INovelDownload
 * @Description TODO
 * @Author XinChen
 * @Date 2019/5/9 22:19
 * @Version 1.0
 **/
public interface INovelDownload {
    /**
     * 整本小说的下载功能 返回下载地址
     * @param url
     * @return
     */
    public String download(String url, Configuration config);


}

package novel.spider;

/**
 * 已经被支持的小说网站枚举
 */
public enum NovelSiteEnum {
    /**
     * 顶点小说网站
     */
    DingDianXiaoShuo(1,"booktxt.net"),
    /**
     *笔趣阁小说网站
     */
    BiQuGe(2,"biquge.biz"),
    /**
     * 笔下
     */
    BiXia(3,"bixia.org"),
    /**
     * 笔下文学
     */
    BiXiaWenXue(4,"bxwx9.org");
    private  int id;
    private String url;
    private  NovelSiteEnum(int id,String url){
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public static NovelSiteEnum getEnumById(int id){
        switch (id){
            case 1 : return DingDianXiaoShuo;
            case 2 : return BiQuGe;
            case 3 : return BiXia;
            default: throw new RuntimeException("id:"+id+"是不被支持的小说网站");
        }
    }
    public static NovelSiteEnum getEnumByUrl(String url){
         for(NovelSiteEnum novelSiteEnum : values()){
             if(url.contains(novelSiteEnum.url)){
                 return novelSiteEnum;
             }
         }
        throw new RuntimeException("url:"+url+"是不被支持的小说网站");
    }
}

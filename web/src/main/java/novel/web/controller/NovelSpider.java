package novel.web.controller;

import novel.spider.entity.Chapter;
import novel.spider.entity.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.ChapterDetailSpiderFactory;
import novel.spider.util.ChapterSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import novel.web.entitys.JSONResponse;
import novel.web.service.NovelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Classname NovelSpider
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/19 1:57
 * @Version 1.0
 **/
@Controller
public class NovelSpider {
    @Resource
    private NovelService service;
    static {
        NovelSpiderUtil.setConfigPath("F:\\opt\\Spider-Rule.xml");
    }
    @RequestMapping(value = "/chapter.do",method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getsChapter(String url){
        IChapterSpider spider = ChapterSpiderFactory.getChapterDetailSpider(url);
        List<Chapter> chapters = spider.getsChapter(url);
        return JSONResponse.success(chapters);
    }

    @RequestMapping(value = "/chapterdetail.do",method = RequestMethod.GET)
    @ResponseBody
    public JSONResponse getDetail(String url){
        IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChaptgerDetailSpider(url);
        ChapterDetail detail = spider.getChapterDetail(url);
        return JSONResponse.success(detail);
    }


    @RequestMapping(value = "/novelSearch.do",method = RequestMethod.POST )
    @ResponseBody
    public JSONResponse getNovleByKeyword(String keyword) throws UnsupportedEncodingException {
        keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(keyword);
        return JSONResponse.success(service.getsNovelByKeyword(keyword));
    }

    @RequestMapping(value = "/novelSearch2.do",method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse getNovelByKeyword(String keyword, int platformId) throws UnsupportedEncodingException {
        keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(keyword);
        System.out.println(platformId);
        return JSONResponse.success(service.getsNovelByKeyword(keyword,platformId));
    }
}

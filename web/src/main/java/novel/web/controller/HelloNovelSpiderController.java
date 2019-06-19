package novel.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname HelloNovelSpiderController
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/19 1:37
 * @Version 1.0
 **/

@Controller
public class HelloNovelSpiderController {
   @RequestMapping(value = "/Hello.do",method = RequestMethod.GET)
   @ResponseBody
    public String sayHello(){

        return " Hello novelSpider";
    }
}

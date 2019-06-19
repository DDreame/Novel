package novel.web.service;

import novel.spider.entity.Novel;
import novel.web.mapper.NovelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname NovelServiceImpl
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/19 2:49
 * @Version 1.0
 **/
@Service
public class NovelServiceImpl implements NovelService {

    @Resource
    private NovelMapper mapper;
    @Override
    public List<Novel> getsNovelByKeyword(String keyword){
        keyword = "%"+keyword+"%";
        System.out.println(keyword);
        return mapper.getsNovelByKeyword(keyword);
    }

    @Override
    public List<Novel> getsNovelByKeyword(String keyword, int platformId) {
        Map<String, String> map = new HashMap<>();
        map.put("platformId",platformId+"");
        map.put("keyword","%"+keyword+"%");
        return mapper.getsNovelByKeyword2(map);
    }

}

package novel.storage.mapper;

import novel.spider.entity.Novel;

import java.util.List;

/**
 * @InterfaceName NovelMapper
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/15 22:18
 * @Version 1.0
 **/
public interface NovelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Novel record);

    int insertSelective(Novel record);

    Novel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Novel record);

    int updateByPrimaryKey(Novel record);

    void batchInsert(List<Novel> novels);

    List<Novel> getsNovelByKeyword(String keyword);
}
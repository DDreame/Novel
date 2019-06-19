import novel.storage.Processor;
import novel.storage.impl.BxwxNovelStorage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

/**
 * @Classname TestCaseO
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/15 23:01
 * @Version 1.0
 **/
public class TestCaseO {
    @Test
    public void testMybatisConnection() throws Exception {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(reader).openSession();
        System.out.println(sqlSession);
    }
    @Test
    public void testKanShuZhongProcess() throws Exception{
        Processor processor = new BxwxNovelStorage();
        processor.process();
    }
}

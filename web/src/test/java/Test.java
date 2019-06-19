import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * @Classname Test
 * @Description TODO
 * @Author XinChen
 * @Date 2019/6/19 12:57
 * @Version 1.0
 **/
public class Test {
    @org.junit.Test
    public void testMybatisConnection() throws Exception {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(reader).openSession();
        System.out.println(sqlSession);
    }
}

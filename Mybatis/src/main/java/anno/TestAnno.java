package anno;

import entity.User;
import org.apache.ibatis.session.SqlSession;
import util.MybatisUtil;

import java.util.List;

public class TestAnno {

    SqlSession sqlSession = MybatisUtil.getSqlSession();
    UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);


    // 测试查询
    @org.junit.jupiter.api.Test
    public void testSelect(){

        List<User> users = mapper.getUser();
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    // 测试更新
    @org.junit.jupiter.api.Test
    public void testUpdate(){

        int i = mapper.updateUser(new User(7,"李2","222"));
        System.out.println(i==0?"失败":"成功");
        sqlSession.close();
    }

    // 测试删除
    @org.junit.jupiter.api.Test
    public void testDelete(){
        int i = mapper.deleteUser(9);
        System.out.println(i==0?"失败":"成功");
        sqlSession.close();
    }

    // 新增用户
    @org.junit.jupiter.api.Test
    public void testInsert(){
        int i = mapper.addUser(new User(10,"张","1111"));
        System.out.println(i==0?"失败":"成功");
        sqlSession.close();
    }
}

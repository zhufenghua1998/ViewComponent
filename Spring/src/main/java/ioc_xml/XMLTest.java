package ioc_xml;

import entity.User;
import entity.User_Complex;
import entity.User_LI;
import entity.User_Ly;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLTest {


    @Test
    public void testBase(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//      基础模式
        User user = ac.getBean("user", User.class);
        System.out.println(user);

//      测试别名
        User user2 = ac.getBean("toName",User.class);
        System.out.println(user2);


        // 检测是否单例模式
        System.out.println(user == user2);

        User user_pro1 = ac.getBean("user_pro", User.class);
        User user_pro2 = ac.getBean("user_pro",User.class);
        System.out.println(user_pro1==user_pro2);

//        测试静态工厂
        User userByStaticFac = ac.getBean("UserByStaticFac", User.class);
        System.out.println(userByStaticFac);


//      测试实例工厂
        User instanceFactory = ac.getBean("instanceFactory", User.class);
        System.out.println(instanceFactory);
    }

    @Test
    public void testLy(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//      测试惰性初始化
        System.out.println("...");
        ac.getBean("user_ly",User_Ly.class);
    }

//   测试构造器
    @Test
    public void testDI(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        System.out.println(ac.getBean("cons_user", User.class));

        System.out.println(ac.getBean("cons2_user", User.class));
    }

//    测试生命周期
    @Test
    public void testLive(){
        // 加载bean容器时，bean被创建...
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        System.out.println("...");
        System.out.println(ac.getBean("live", User_LI.class));
        System.out.println("...");
        ac.close();       // 装载bean容器被销毁时，触发...
        System.out.println("...");
    }

//    Set注入
    @Test
    public void testSet(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        System.out.println(ac.getBean("set_user1", User.class));

        System.out.println(ac.getBean("set_user2",User_Complex.class));
    }
}

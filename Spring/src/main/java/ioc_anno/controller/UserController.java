package ioc_anno.controller;

import ioc_anno.pojo.User;
import ioc_anno.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationAnnotation.xml");

        // 默认生成小写名
        User user = ac.getBean("user", User.class);
        System.out.println(user);

        // 切记首字母自动变小写，其余不变
        UserService userService = ac.getBean("userService", UserService.class);
        System.out.println(userService.update(user));
    }
}

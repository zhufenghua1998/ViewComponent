package aop_anno;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOP_ANNO_TEST {


    @Test
    public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationAopAnno.xml");
        Target target = (Target)ac.getBean("target");
        target.save("张三");
    }
    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationAopAnno.xml");
        Target2 target = (Target2)ac.getBean("target2");
        target.save("张三");
    }
}

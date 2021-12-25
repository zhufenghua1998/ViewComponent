package aop_xml;

import entity.Target;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOP_XML_TEST {



    @Test
    public void testBase(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationAOPContext.xml");
        Target t = (Target) ac.getBean("target");
        t.save("zhangsan");
    }
    @Test
    public void testAround(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationAOPContext2.xml");
        Target t2 = (Target) ac.getBean("target2");
        t2.save("zhangsan2");
    }
}

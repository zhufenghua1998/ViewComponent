package aop_anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestBefore {

    @Before("execution(* aop_anno.*.*(*))")
    public void doBefore(JoinPoint jp){
        System.out.println("=================");
        System.out.println("执行前置通知");
        System.out.println("获取传输给目标方法的参数"+jp.getArgs()[0]);
        System.out.println("获取目标方法中的java反射对象"+jp.getSignature());
        System.out.println("=================");
    }
}

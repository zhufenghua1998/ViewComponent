package aop_anno;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAfter {

    //后置通知注解  @AfterReturning
    @AfterReturning(value = "execution(* aop_anno.Target.*(*))",returning = "obj")
    public void doAfter(Object obj){
        System.out.println("======================");
        System.out.println("执行后置通知");
        System.out.println("obj是目标方法的返回值"+obj);
        System.out.println("======================");
    }
}

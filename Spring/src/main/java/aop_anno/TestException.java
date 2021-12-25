package aop_anno;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestException {

    //如果目标程序产生异常，则后置通知不会执行
    @AfterThrowing(value = "execution(* aop_anno.*.*(*))",throwing = "e")
    public void doException(Throwable e){
        System.out.println("================");
        System.out.println("执行异常通知");
        System.out.println(e.getMessage());
        System.out.println("================");
    }
}

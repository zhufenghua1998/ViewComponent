package aop_anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAround {
    //需要将其他四个通知注释掉
    @Around("execution(* aop_anno.Target2.*(*))")
    /*ProceedingJoinPoint继承JoinPoint 多了个proceed方法用来调用目标程序*/
    public void doAround(ProceedingJoinPoint pjp){
        System.out.println("执行前置通知");
        try {
            /*调用目标方法执行*/
            Object o = pjp.proceed();
            System.out.println("执行后置通知  "+o);
        } catch (Throwable throwable) {
            System.out.println("执行异常通知");
            throwable.printStackTrace();
        }
        System.out.println("执行最终通知");
    }
}

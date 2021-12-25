package entity;

import org.aspectj.lang.ProceedingJoinPoint;

public class TargetAround {

    //ProceedingJoinPoint 继承了JoinPoint
    //在JoinPoint基础上暴露了proceed方法
    //proceed很重要   这个是AOP代理链的执行方法
    public void doAround(ProceedingJoinPoint pjp){
        System.out.println("前置通知"+pjp.getArgs()[0]);
        try {

            //调用目标方法
            Object o = pjp.proceed();
            System.out.println("后置通知"+o);
        } catch (Throwable throwable) {
            System.out.println("执行异常通知");
            throwable.printStackTrace();
        }
        System.out.println("执行最终通知");
    }
}

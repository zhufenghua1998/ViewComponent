package entity;

import org.aspectj.lang.JoinPoint;

public class TargetAfter {

    public void doAfter(JoinPoint jp, Object obj){
        System.out.println("执行后置通知");
        System.out.println("obj是目标方法的返回值"+obj);
    }
}

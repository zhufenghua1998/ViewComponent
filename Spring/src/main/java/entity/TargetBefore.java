package entity;

import org.aspectj.lang.JoinPoint;
//配置前置通知
public class TargetBefore {

    /*
     * JoinPoint是程序运行中可以识别的点，这个点可以作为aop的切入点
     * 包含了很多方法  比如切入点的对象、方法、属性  我们可以通过反射动态获取这些点的状态和信息
     * 用于目标追踪(tracing)和日志记录(logging)应用信息
     * */
    public void doBefore(JoinPoint jp){
        System.out.println("执行前置通知");
        System.out.println("获取传递给目标方法的参数值："+jp.getArgs()[0]);
        System.out.println("获取目标方法的java反射对象"+jp.getSignature());
    }
}
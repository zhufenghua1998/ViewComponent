package aop_anno;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestFinal {
    @After("execution(* aop_anno.*.*(*))")
    public void doFinal(){
        System.out.println("================");
        System.out.println("执行最终通知");
        System.out.println("================");
    }
}

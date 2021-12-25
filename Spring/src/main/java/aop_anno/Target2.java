package aop_anno;

import org.springframework.stereotype.Component;

// 组件
@Component
public class Target2 {

    //切入点
    public String save(String name){
        System.out.println("目标方法执行中");
        int i = 10 / 0;
        return  name;
    }
}

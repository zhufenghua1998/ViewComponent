package entity;

//目标程序
public class Target {

    public String save(String name){
        System.out.println("目标方法执行中.....");
        int i = 10 / 0;
        return name;
    }
}
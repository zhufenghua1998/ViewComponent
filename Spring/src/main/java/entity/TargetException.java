package entity;

public class TargetException {

    public void doException(Throwable e){
        System.out.println("执行异常通知，异常信息为"+e.getMessage());
    }
}

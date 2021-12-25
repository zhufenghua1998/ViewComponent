package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class ViewDate {

    /**
     * @return 1.1、返回当前日期的字符串（年、月、日、时、分、秒）
     */
    public static String now(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * @return 1.2、返回当前时间（时，分，秒）
     */
    public static String time(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * @return 1.3、返回当前日期的（年、月、日）
     */
    public static String today(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    /**
     * 模块测试
     */
    public static void main(String[] args) {
        System.out.println(today());
        System.out.println(time());
        System.out.println(now());
    }
}

package util;

import java.util.UUID;

/**
 * 针对java.util.UUID类进行再次封装
 */
public class ViewUuid {


    /**
     * @return 包含-的32位字符串
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    public static String simpleUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 模块测试
     */
    public static void main(String[] args) {
        System.out.println(randomUUID());
        System.out.println(simpleUUID());
    }
}

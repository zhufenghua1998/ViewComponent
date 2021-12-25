package util;

/**
 * 字符串工具类
 */
public class ViewStr {

    /**
     * 生成一个查询关键字
     */
    public static String like(String key){
        if(key==null){
            key = "";
        }
        return "%"+key+"%";
    }
}

package util;

import java.sql.*;
import java.util.*;

/**
 *           -- 基本介绍 --
 * 高级Jdbc工具类封装，支持原生jdbc，不再支持数据库连接池
 * 依赖：1.数据库驱动，3.配置文件(viewJdbc.properties)
 * 其他说明：{JDK：8+，数据库支持：不再支持任意数据库，仅Mysql}
 配置文件jdbc.properties示例：

 driverClassName=com.mysql.jdbc.Driver
 url=jdbc:mysql://localhost.com:3306/viewComponent
 username=ViewTools
 password=L3ca7Z8ANWMfRkD4

 上述对应版本的Mysql之Maven坐标示例：

 <dependency>
 <groupId>mysql</groupId>
 <artifactId>mysql-connector-java</artifactId>
 <version>5.1.6</version>
 </dependency>
 *
 *           -- 功能介绍 --
 * ①基础封装
 * 1.2、getConnection  ==》 获取连接对象
 * 1.3、close  ==》 关闭连接对象
 *
 * ②基础SQL语句（复用指的是参数中传入Connection对象，在执行后不释放该conn)
 * 2.1、update：简化、复用  |  通用的insert, delete, update语句
 * 2.3、queryForMap：简化、复用  |  查询为<String,Object>格式的Map
 * 2.5、queryMapList：简化、复用 | 查询为List<Map<String,Object>>
 *
 * ③高级语句，
 * 3.1、insert：简化 | 复用，插入一条数据，并返回自增的ID
 * 3.2、getPage： 简化 | 复用，分页查询包含数据和总数，以MapList形式返回，最后一个Map表示pageInfo对象
 *
 *          -- 关于Map与Object --
 *      不再支持Object，全部封装为Map对象
 */
public class ViewJDBC {

    // properties配置文件名
    private static final String proName = "viewJdbc.properties";


    // 基础连接信息
    private static String url;
    private static String username;
    private static String password;

    static{
        try {
            //1.加载配置文件
            Properties config = new Properties();
            config.load(ViewJDBC.class.getClassLoader().getResourceAsStream(proName));
            //2.加载配置
            //基本连接信息
            String driver = config.getProperty("driverClassName");
            url = config.getProperty("url");
            username = config.getProperty("username");
            password = config.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 1.2、获取连接方法
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn =  DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 1.3.1、归还连接，1参数
     */
    public static void close(Connection conn){
        close(conn,null,null);
    }

    /**
     * 1.3.2、归还连接，2参数
     */
    public static void close(Statement stmt, ResultSet rs){
        close(null,stmt, rs);
    }

    /**
     * 1.3.3、归还连接，3参数
     */
    public static void close(Connection conn,Statement stmt, ResultSet rs){

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 2.1.1、支持复用的update
     */
    public static int update(Connection conn,String sql,Object...args){
        int rs=0;
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(ps,null);
        }
        return rs;
    }

    /**
     * 2.1.2、简化的update
     */
    public static int update(String sql,Object...args){
        Connection conn = getConnection();
        int rs = update(conn,sql,args);
        close(conn);
        return rs;
    }


    /**
     * 把rs列名和值送入map的key和value中
     * @param rs 原ResultSet类
     * @param map 目标map
     * @throws SQLException 调用者处理
     */
    private static void rsToMap(ResultSet rs, Map<String,Object> map) throws SQLException {
        ResultSetMetaData rsMd = rs.getMetaData();
        for (int i = 0; i < rsMd.getColumnCount(); i++) {
            String name = rsMd.getColumnName(i+1);
            name = name.toLowerCase();
            Object object = rs.getObject(name);
            map.put(name,object);
        }
    }


    /**
     * 2.3.1、支持复用的queryForMap
     * @param conn 连接对象
     * @param sql 预执行的sql
     * @param args 参数
     * @return Map
     */

    public static Map<String,Object> queryForMap(Connection conn, String sql, Object... args){
        Map<String,Object> map = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                rsToMap(rs,map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(ps,rs);
        }
        return map;
    }


    /**
     * 2.3.2、简化的queryForMap，查询一行数据封装为Map，兼容性极好
     */
    public static Map<String,Object> queryForMap(String sql, Object... args){
        Connection conn = getConnection();
        Map<String,Object> map = queryForMap(conn,sql,args);
        close(conn);
        return map;
    }


    /**
     * 2.5.1、支持复用的queryMapList
     * @param conn 连接对象
     * @param sql 预执行的sql
     * @param args 参数
     * @return MapList集合
     */
    public static List<Map<String,Object>> queryMapList(Connection conn, String sql, Object... args){
        List<Map<String,Object>> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(sql);
            for (int i = 0; i <args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            while (rs.next()){
                Map<String,Object> map = new HashMap<>();
                rsToMap(rs,map);
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ps,rs);

        }
        return list;
    }


    /**
     * 2.5.2、queryMapList，使用List的map集合返回原始数据，兼容性极好
     */
    public static List<Map<String,Object>> queryMapList(String sql, Object... args){
        Connection conn =  getConnection();
        List<Map<String,Object>> list = queryMapList(conn,sql,args);
        close(conn);
        return list;
    }

    /**
     * 3.1.1、支持复用的Mysql插入一条数据并获取自增主键的方法
     */
    public static Long insert(Connection conn, String sql, Object...args){
        long insert = update(conn, sql, args);
        if(insert==1){
            insert = Long.parseLong(queryForMap(conn, "select LAST_INSERT_ID() id").get("id").toString());
        }
        return insert;
    }

    /**
     * 3.1.2、简化的Mysql插入一条数据并获取自增主键的方法
     */
    public static Long insert(String sql, Object...args){
        Connection conn = getConnection();
        Long insert = insert(conn, sql, args);
        close(conn);
        return insert;
    }


    /**
     * 3.2.1、支持复用的的分页查询
     * @param pageNum 要获取第几页
     * @param pageSize 一页中要几条数据
     * @param sql 预设sql，保持原查询，如 select * from paper 结尾不可写分号
     * @param args 对应的sql参数
     * @return 类似queryMapList，返回页面数据，最后一个Map代表pageInfo对象
     */
    public static List<Map<String,Object>> getPage(Connection conn,long pageNum, long pageSize, String sql,Object...args){
        List<Map<String,Object>> mapList;
        long start = (pageNum-1) * pageSize;
        // 在select关键字后插入sql_calc_founds_rows
        String key = "select";
        StringBuilder sb = new StringBuilder(sql);
        sb.insert(sql.indexOf(key)+key.length()," sql_calc_found_rows ");
        // 在sql后拼接limit
        sb.append(" limit ?,?");
        String pre_sql = sb.toString();
        List<Object> list = new ArrayList<>(Arrays.asList(args));
        list.add(start);
        list.add(pageSize);
        Object[] objects = list.toArray();
        // 取得分页数据
        mapList = queryMapList(conn,pre_sql, objects);
        // 开始封装pageInfo对象
        Map<String,Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        // 获取结果条数
        long total = Long.parseLong(queryForMap(conn,"SELECT FOUND_ROWS() rows").get("rows").toString());
        pageInfo.put("total",total);
        // 计算总页数
        long pages = (long) Math.ceil(total *1.0 / pageSize);
        pageInfo.put("pages",pages);
        // 当前页数据量
        long size = mapList.size();
        pageInfo.put("size",size);
        // 页面判断
        pageInfo.put("isFirstPage",pageNum == 1);
        pageInfo.put("isLastPage",pageNum == pages);
        pageInfo.put("hasPrePage",pageNum>1);
        pageInfo.put("hasNextPage",pageNum<pages);
        pageInfo.put("prePage",pageNum-1);
        pageInfo.put("nextPage",pageNum+1);
        mapList.add(pageInfo);
        return mapList;
    }

    /**
     * 3.2.2、简化的Mysql分页
     */
    public static List<Map<String,Object>> getPage(long pageNum, long pageSize, String sql,Object...args){

        Connection conn = getConnection();
        List<Map<String, Object>> page = getPage(conn,pageNum,pageSize,sql,args);
        close(conn);

        return page;
    }

    /**
     * 模块测试
     */
    public static void main(String[] args) {
        // 1.2、获取连接对象
        Connection conn = getConnection();
        // 1.3、关闭连接对象
        close(conn);

        // 2.1、执行update语句
        System.out.println(update("delete from user where id=?",3));
        // 2.3、查询一行为Map
        System.out.println(queryForMap("select * from user where id=1"));
        // 2.5、查询多行为MapList
        System.out.println(queryMapList("select * from user"));

        // 3.1、插入数据并返回自增ID
        System.out.println(insert("insert into user(name,pwd) values(?,?)", "张三", "123"));
        // 3.2、分页第1页，每页3条数据
        System.out.println(getPage(1,3,"select * from user"));
    }
}

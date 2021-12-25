package util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 简单Servlet封装：不区分请求方式，一个Mapping对应一个方法名
 */
public class ViewServlet extends HttpServlet {

    protected HttpServletRequest req;
    protected HttpServletResponse resp;
    protected Gson gson = new Gson();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        // 请求与响应对象内置到类中
        this.req = req;
        this.resp = resp;

        // 1.取得servlet路径
        String servletPath = req.getServletPath();
        // 2.获取应处理该servlet的方法名（去掉前置目录）
        String method_name = servletPath.substring(servletPath.lastIndexOf("/")+1);
        // 3.尝试执行此方法
        try {
            // 设置编码
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");

            // 仅public，无参方法有效
            Method method = this.getClass().getMethod(method_name);
            method.invoke(this);

        }catch (NoSuchMethodException e){
            System.out.println("正在请求URL："+servletPath+"，但 ViewServlet 类中没有对应的方法");
        }catch(InvocationTargetException | IllegalAccessException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // 提供子类继承方法示例
    public void viewService(){

    }
    /**
     * 1.4.2 快速返回数字给前端，适用于查询操作或其他操作
     */
    protected void number(Long number){
        try {
            resp.getWriter().write(""+number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.4.3 快速返回Json数据给前端，适用于查询操作或其他操作
     */
    protected  void json(String json){
        resp.setContentType("application/json;charset=utf-8");
        try {
            resp.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 1.4.4 返回通用的成功信息，适用于增删改等操作
     */
    protected void success(){
        resp.setContentType("application/json;charset=utf-8");
        try {
            resp.getWriter().write("{\"errno\":0,\"msg\":\"success\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.4.5 返回通用的失败信息，适用于增删改操作
     */
    protected void error(){
        resp.setContentType("application/json;charset=utf-8");
        try {
            resp.getWriter().write("{\"errno\":1,\"msg\":\"error\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.4.6 自动判断成功与失败，并快速返回信息
     */
    protected void test(long status){
        // 返回值 <= 0 通常用作失败表示，比如sql操作
        if(status<=0){
            error();
        }else{
            success();
        }
    }

    /**
     * 如果有多个字段，均会获取出来
     * @return 获取对应的属性值列表
     */
    protected Object[] getValues(HttpServletRequest req,String...names){
        List<Object> list= new ArrayList<>();
        for (String name : names) {
            String[] parameterValues = req.getParameterValues(name);
            if(parameterValues!=null){
                list.addAll(Arrays.asList(parameterValues));
            }
        }
        return list.toArray();
    }


    /**
     * 1.5、获取所有参数
     */
    protected Map<String, List<String>> getParams(HttpServletRequest req){
        Map<String,List<String>> params = new HashMap<>();
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<Map.Entry<String,String[]>> set = parameterMap.entrySet(); //1.获取所有的 entry
        for(Map.Entry<String,String[]> entry : set){ //2.使用增强 for 遍历 entry
            String key = entry.getKey();  //3.取得 key
            String[] value = entry.getValue();
            List<String> list = new ArrayList<>(Arrays.asList(value));
            params.put(key,list);
        }
        return params;
    }

}

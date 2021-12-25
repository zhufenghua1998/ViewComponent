package test;

import util.ViewJDBC;
import util.ViewServlet;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@WebServlet({"/index/getUser","/index/update"})
public class TestServlet extends ViewServlet {

    // 获取指定用户信息
    public void getUser(){
        System.out.println("getUser...");
        String id = req.getParameter("id");
        Map<String, Object> user = ViewJDBC.queryForMap("select * from user where id=?", id);
        json(gson.toJson(user));
    }

    public void update(){
        System.out.println("update...");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        test(ViewJDBC.update("update user set name=?,pwd=? where id=?", name, pwd, id));
    }
}

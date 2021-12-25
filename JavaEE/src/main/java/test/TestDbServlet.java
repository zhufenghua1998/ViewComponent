package test;

import util.ViewServletDb;

import javax.servlet.annotation.WebServlet;

// 每个mapping都需要配置对应的方法，方法名为缩略的mapping
@WebServlet({"/addUser","/delete","/user/update","/getAllUser",
        "/user/query","/getUserPage","/user/insert"})
public class TestDbServlet extends ViewServletDb {

    // 增加用户
    public void addUser() {
        System.out.println("addUser...");
        update("insert into user(name,pwd) values(?,?)","name","pwd");
    }

    // 查询单个用户信息
    public void query(){
        System.out.println("query...");
        query("select * from user where id=?","id");
    }

    // 修改用户
    public void update(){
        System.out.println("update...");
        update("update user set name=?,pwd=? where id=?","name","pwd","id");
    }

    // 删除用户
    public void delete(){
        System.out.println("delete...");
        update("delete from user where id=?","id");
    }

    // 查询所有用户
    public void getAllUser(){
        System.out.println("getAllUser...");
        queryAll("select * from user");
    }

    // 分页获取用户
    public void getUserPage(){
        System.out.println("getUserPage...");
        getPage("pageNum","pageSize","select * from user");
    }

    // 新增一个用户，并获得它的主键
    public void insert(){
        System.out.println("insert...");
        insert("insert into user(name,pwd) values(?,?)","name","pwd");
    }
}

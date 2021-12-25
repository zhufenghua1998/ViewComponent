package util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 请设置启用，并适当修改
//@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        System.out.println("请求资源路径："+path);
        // 未登录放行资源
        if(path.equals("/index.jsp") || path.startsWith("/static")){
            chain.doFilter(request,response);
        }
        //登录校验不通过，引导重定向页面
        else if (request.getSession().getAttribute("user")==null){
            response.sendRedirect(request.getContextPath()+"/static/error.html");
        }else{
            // 登录检测通过，放行
            chain.doFilter(request,response);
        }
    }
}

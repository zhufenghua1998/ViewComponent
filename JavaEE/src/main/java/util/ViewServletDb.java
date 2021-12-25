package util;


/**
 * 自动基于完成http的数据库操作
 */
public class ViewServletDb extends ViewServlet {

    /**
     * 查询多条数据
     * @param sql 查询的sql
     * @param names 前端传递的names关键字对，与sql参数顺序对应
     */
    protected void queryAll(String sql,String...names){
        json(gson.toJson(ViewJDBC.queryMapList(sql,this.getValues(req,names))));
    }

    /**
     * 查询一条数据
     * @param sql 查询的sql
     * @param names 前端传递的names关键字对，与sql参数顺序对应
     */
    protected void query(String sql,String...names){

        json(gson.toJson(ViewJDBC.queryForMap(sql,getValues(req,names))));

    }

    /**
     *
     * @param sql 更新的sql语句
     * @param names 前端传递的names关键字数组，与sql参数顺序对应
     */
    protected void update(String sql,String...names){
        test(ViewJDBC.update(sql,getValues(req,names)));
    }

    /**
     * 自动获取分页
     * @param pageNum 当前页数，前端传递的参数名
     * @param pageSize 每页条数，前端传递的参数名
     * @param sql 要执行的sql
     * @param names 前端传递的names关键字数组，与sql参数顺序对应
     */
    protected void getPage(String pageNum, String pageSize, String sql,String...names){
        if(req.getParameter(pageNum)==null){
            System.out.println("pageNum为空");
            error();
        }else if(req.getParameter(pageSize)==null){
            System.out.println("pageSize为空");
            error();
        }else{
            long num = Long.parseLong(req.getParameter(pageNum));
            long size = Long.parseLong(req.getParameter(pageSize));
            json(gson.toJson(ViewJDBC.getPage(num,size,sql,getValues(req,names))));
        }
    }

    /**
     * 插入一条数据，并返回主键
     * @param sql 要执行的sql
     * @param names 前端传递的names关键字数组，与sql参数顺序对应
     */
    protected void insert(String sql,String...names){
        number(ViewJDBC.insert(sql,getValues(req,names)));
    }
}

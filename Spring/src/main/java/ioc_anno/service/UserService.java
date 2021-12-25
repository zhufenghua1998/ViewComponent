package ioc_anno.service;

import ioc_anno.dao.UserDaoImpl;
import ioc_anno.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

//    @Resource
//    这是Java原生注解，基本相同
//    两者默认都是byType，注意这里的Type指短类名，即UserDaoImpl，而byName是指Bean的id或者name
    @Autowired
    private UserDaoImpl udi;

    public int update(User u){
        return udi.update(u);
    }
}

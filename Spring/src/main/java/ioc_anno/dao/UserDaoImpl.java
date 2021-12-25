package ioc_anno.dao;

import ioc_anno.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{


    @Override
    public int update(User user) {
        return 0;
    }
}

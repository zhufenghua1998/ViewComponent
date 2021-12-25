package xml;

import entity.User;

import java.util.List;

public interface UserMapper {
    List<User> getUser();

    int updateUser(User user);

    int deleteUser(int id);

    int addUser(User user);
}
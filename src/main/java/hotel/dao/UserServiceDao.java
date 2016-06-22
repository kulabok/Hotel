package hotel.dao;

import hotel.entity.User;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface UserServiceDao {
    User exist(String login, String password);
    boolean add(User user);
    void delete(int id);
    User findById(int id);
    User edit(User user);
    List<User> findAll();
    User findByLoginAndPassword(String login, String password);
}

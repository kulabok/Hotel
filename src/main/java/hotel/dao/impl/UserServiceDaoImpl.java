package hotel.dao.impl;

import hotel.dao.UserServiceDao;
import hotel.dao.repository.UserRepository;
import hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class UserServiceDaoImpl implements UserServiceDao {
    @Autowired
    private UserRepository userRepository;

    public User exist(String login, String password){
        return userRepository.exist(login, password);
    }

    @Override
    public boolean add(User user) {
        boolean success = false;
        User savedUser = userRepository.saveAndFlush(user);
        if (savedUser!=null) success = true;
        return success;
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findById(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public User edit(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}

package hotel.dao.impl;

import hotel.config.TestDataBaseConfig;
import hotel.dao.UserServiceDao;
import hotel.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by kulabok on 15.06.2016.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
public class UserServiceDaoImplTest {
    @Resource
    private EntityManagerFactory emf;
    private EntityManager em;
    @Resource
    private UserServiceDao userDao;


    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void exist() throws Exception {
        assertTrue(userDao.exist("Peter", "Pan" ) != null);
    }

    @Test
    public void add() throws Exception {
        User u = new User();
        u.setFullName("Chuck Norris");
        u.setLogin("Chuck");
        u.setPassword("Norris");
        u.setAdmin(false);
        userDao.add(u);
        assertTrue(userDao.findByLoginAndPassword("Chuck", "Norris").getFullName().equals("Chuck Norris"));
        userDao.delete(userDao.findByLoginAndPassword("Chuck", "Norris").getId());
    }

    @Test
    public void delete() throws Exception {
        User u = new User();
        u.setFullName("Arni Schvartz");
        u.setLogin("Arni");
        u.setPassword("Schvartz");
        u.setAdmin(false);
        userDao.add(u);
        userDao.delete(userDao.findByLoginAndPassword("Arni", "Schvartz").getId());
                assertTrue(userDao.findByLoginAndPassword("Arni", "Schvartz") == null);
    }

    @Test
    public void findById() throws Exception {
        List<User> userList = userDao.findAll();
        for (User anUserList : userList) {
            int k = anUserList.getId();
            assertTrue(userDao.findById(k) != null);
        }
    }

    @Test
    public void edit() throws Exception {
        User u = new User();
        u.setFullName("Very Angry Admin");
        u.setLogin("Very Angry");
        u.setPassword("Admin");
        userDao.add(u);
        User user = userDao.findByLoginAndPassword("Very Angry", "Admin");
        user.setLogin("Domovenok");
        user.setPassword("Kuzya");
        user.setFullName("Domovenok Kuzya");
        userDao.edit(user);
        assertTrue(userDao.findByLoginAndPassword("Very Angry", "Admin") == null);
        assertTrue(userDao.findByLoginAndPassword("Domovenok", "Kuzya").getFullName().equals("Domovenok Kuzya"));
        userDao.delete(userDao.findByLoginAndPassword("Domovenok", "Kuzya").getId());
    }

    @Test
    public void findAll() throws Exception {
        List<User> usersList = userDao.findAll();
        assertTrue(usersList.size()>0);
    }

    @Test
    public void findByLoginAndPassword() throws Exception {
        assertTrue(userDao.findByLoginAndPassword("Andrew", "Kochkin").getFullName().equals("Andrew Kochkin"));
        assertTrue(userDao.findByLoginAndPassword("Jack", "Sparrow").getFullName().equals("Jack Sparrow"));
    }
}
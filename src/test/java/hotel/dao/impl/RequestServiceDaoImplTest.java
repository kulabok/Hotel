package hotel.dao.impl;

import hotel.config.TestDataBaseConfig;
import hotel.dao.RequestServiceDao;
import hotel.dao.UserServiceDao;
import hotel.entity.Request;
import hotel.entity.User;
import hotel.entity.enums.RoomClass;
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

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
public class RequestServiceDaoImplTest {
    @Resource
    private EntityManagerFactory emf;
    private EntityManager em;

    @Resource
    private UserServiceDao userDao;
    @Resource
    private RequestServiceDao requestDao;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testExist() throws Exception {
        assertTrue(requestDao.exist(1));
        assertTrue(requestDao.exist(2));
        assertTrue(requestDao.exist(3));
        assertTrue(requestDao.exist(4));
        assertTrue(requestDao.exist(5));
    }

    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setFullName("Adam Smith");
        user.setLogin("Adam");
        user.setPassword("Smith");
        userDao.add(user);
        user.setId(userDao.findByLoginAndPassword("Adam", "Smith").getId());
        Request r = new Request();
        r.setUser(userDao.findById(user.getId()));
        r.setRoomClass(RoomClass.LUX);
        r.setPersonQuantity(22222);
        r.setStart(Date.valueOf("2016-06-16"));
        r.setEnd(Date.valueOf("2016-07-18"));
        r.setProcessed(false);
        requestDao.add(r);
        int index = 0;
        List<Request> requestList = requestDao.findAll();
        for (int i = 0; i < requestList.size(); i++) {
            if (requestList.get(i).getUser().getId() == user.getId() && !requestList.get(i).isProcessed()){
                index = requestList.get(i).getId();
                assertTrue(requestList.get(i).getPersonQuantity() == 22222);
                break;
            }
        }
        requestDao.delete(index);
    }

    @Test
    public void testDelete() throws Exception {
        User user = new User();
        user.setFullName("Shrek The Green");
        user.setLogin("Shrek");
        user.setPassword("The Green");
        userDao.add(user);
        Request r = new Request();
        r.setUser(userDao.findByLoginAndPassword("Shrek", "The Green"));
        r.setRoomClass(RoomClass.LUX);
        r.setPersonQuantity(23);
        r.setStart(Date.valueOf("2016-06-16"));
        r.setEnd(Date.valueOf("2016-07-18"));
        r.setProcessed(false);
        requestDao.add(r);
        List<Request> reqList= requestDao.findAll();
        Request request = null;
        for (int i = 0; i < reqList.size(); i++) {
            if (reqList.get(i).getUser().getFullName().equals("Shrek The Green")){
                request = reqList.get(i);
            }
        }
        requestDao.delete(request.getId());
        assertTrue(requestDao.findById(request.getId()) == null);
    }

    @Test
    public void testFindById() throws Exception {
        assertTrue(requestDao.findById(2).getUser().getId() == 2);
        assertTrue(requestDao.findById(3).getUser().getId() == 3);
        assertTrue(requestDao.findById(4).getUser().getId() == 4);
    }

    @Test
    public void testEdit() throws Exception {
        User user = new User();
        user.setFullName("Terminator IllBeBack");
        user.setLogin("Terminator");
        user.setPassword("IllBeBack");
        userDao.add(user);
        Request r = new Request();
        r.setUser(userDao.findByLoginAndPassword("Terminator", "IllBeBack"));
        r.setRoomClass(RoomClass.LUX);
        r.setPersonQuantity(23);
        r.setStart(Date.valueOf("2016-06-16"));
        r.setEnd(Date.valueOf("2016-07-18"));
        r.setProcessed(false);
        requestDao.add(r);
        List<Request> reqList= requestDao.findAll();
        Request request = null;
        for (int i = 0; i < reqList.size(); i++) {
            if (reqList.get(i).getUser().getFullName().equals("Terminator IllBeBack")){
                request = reqList.get(i);
            }
        }
        request.setPersonQuantity(111);
        requestDao.edit(request);
        assertTrue(requestDao.findById(request.getId()).getPersonQuantity() == 111);
        requestDao.delete(request.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<Request> requestList = requestDao.findAll();
        assertTrue(requestList.size()>2);
    }

    @Test
    public void testFindAllByUserAndProcessedIsFalse() throws Exception {
        List<Request> reqList = requestDao.findAllByUserAndProcessedIsFalse(1);
        assertTrue(reqList.size() == 1);
    }

    @Test
    public void testFindAllByUserAndProcessedIsTrue() throws Exception {
        List<Request> reqList = requestDao.findAllByUserAndProcessedIsTrue(2);
        assertTrue(reqList.size() == 1);
    }

    @Test
    public void testFindAllByProcessedIsFalse() throws Exception {
        List<Request> reqList = requestDao.findAllByProcessedIsFalse();
        assertTrue(reqList.size() == 3);
    }

    @Test
    public void testUpdateProcessedToTrue() throws Exception {
        assertFalse(requestDao.findById(1).isProcessed());
        requestDao.updateProcessedToTrue(1);
        assertTrue(requestDao.findById(1).isProcessed());
        requestDao.updateProcessedToFalse(1);
        assertFalse(requestDao.findById(1).isProcessed());
    }
}
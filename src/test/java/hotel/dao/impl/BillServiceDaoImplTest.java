package hotel.dao.impl;

import hotel.config.TestDataBaseConfig;
import hotel.dao.BillServiceDao;
import hotel.dao.RequestServiceDao;
import hotel.dao.RoomServiceDao;
import hotel.dao.UserServiceDao;
import hotel.entity.Bill;
import hotel.entity.Request;
import hotel.entity.Room;
import hotel.entity.User;
import hotel.entity.enums.RoomClass;

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
public class BillServiceDaoImplTest {
    @Resource
    private EntityManagerFactory emf;
    private EntityManager em;

    @Resource
    private RoomServiceDao roomDao;
    @Resource
    private UserServiceDao userDao;
    @Resource
    private RequestServiceDao requestDao;
    @Resource
    private BillServiceDao billDao;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void testExist() throws Exception {
        assertTrue(billDao.exist(billDao.findAll().get(0).getId()));
    }

    @Test
    public void testAdd() throws Exception {
        Bill bill = new Bill();
        User user = new User();
        user.setFullName("John Travolta");
        user.setLogin("John");
        user.setPassword("Travolta");
        userDao.add(user);
        Request request = new Request();
        request.setRoomClass(RoomClass.LUX);
        request.setPersonQuantity(2);
        request.setUser(userDao.findByLoginAndPassword("John", "Travolta"));
        request.setStart(Date.valueOf("2016-07-17"));
        request.setEnd(Date.valueOf("2016-08-18"));
        requestDao.add(request);
        int index = 0;
        List<Request> requestList = requestDao.findAll();
        for (int i = 0; i < requestList.size(); i++) {
            if (requestList.get(i).getUser().getFullName().equals("John Travolta")){
                index = requestList.get(i).getId();
                break;
            }
        }
        bill.setRequest(requestDao.findById(index));
        Room room = new Room();
        room.setNumber(404);
        room.setRoomclass(RoomClass.STANDARD);
        room.setPersonsMax(3);
        room.setCostPerPerson(500);
        room.setAvailable(2);
        roomDao.add(room);
        bill.setRoom(roomDao.findByNumber(404));
        billDao.add(bill);
        List<Bill> billList = billDao.findAll();
        int id = 0;
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getRoom().getNumber() == 404){
                id = billList.get(i).getId();
                assertTrue(billList.get(i).getRoom().getCostPerPerson() == 500);
            }
        }
        billDao.delete(id);
        requestDao.delete(index);
        roomDao.delete(404);
    }

    @Test
    public void testDelete() throws Exception {
        Bill bill = new Bill();
        User user = new User();
        user.setFullName("Mel Gibson");
        user.setLogin("Mel");
        user.setPassword("Gibson");
        userDao.add(user);
        Request request = new Request();
        request.setRoomClass(RoomClass.LUX);
        request.setPersonQuantity(2);
        request.setUser(userDao.findByLoginAndPassword("Mel", "Gibson"));
        request.setStart(Date.valueOf("2016-07-17"));
        request.setEnd(Date.valueOf("2016-08-18"));
        requestDao.add(request);
        int index = 0;
        List<Request> requestList = requestDao.findAll();
        for (int i = 0; i < requestList.size(); i++) {
            if (requestList.get(i).getUser().getFullName().equals("Mel Gibson")){
                index = requestList.get(i).getId();
                break;
            }
        }
        bill.setRequest(requestDao.findById(index));
        Room room = new Room();
        room.setNumber(404);
        room.setRoomclass(RoomClass.STANDARD);
        room.setPersonsMax(3);
        room.setCostPerPerson(500);
        room.setAvailable(2);
        roomDao.add(room);
        bill.setRoom(roomDao.findByNumber(404));
        billDao.add(bill);
        List<Bill> billList = billDao.findAll();
        for (int i = 0; i < billList.size(); i++) {
            if (billList.get(i).getRoom().getNumber() == 404){
                int id = billList.get(i).getId();
                billDao.delete(id);
                assertFalse(billDao.exist(billList.get(i).getId()));
                break;
            }
        }
        requestDao.delete(index);
        roomDao.delete(404);
    }

    @Test
    public void testFindById() throws Exception {
        assertTrue(requestDao.findById(1).getId() == 1);
        assertTrue(requestDao.findById(2).getId() == 2);
        assertTrue(requestDao.findById(3).getId() == 3);
        assertTrue(requestDao.findById(4).getId() == 4);
    }

    @Test
    public void testEdit() throws Exception {
        Bill bill = billDao.findById(2);
        bill.setCost(100500);
        billDao.edit(bill);
        assertTrue(billDao.findById(2).getCost() == 100500);
        bill.setCost(1500);
        billDao.edit(bill);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Bill> billList = billDao.findAll();
        assertTrue(billList.size()> 2);
    }

    @Test
    public void testFindAllByUserAndPaidIsFalse() throws Exception {
        List<Bill> billList = billDao.findAllByUserAndPaidIsFalse(1);
        assertTrue(billList.size() == 1);
    }

    @Test
    public void testFindAllByUserAndPaidIsTrue() throws Exception {
        List<Bill> billList = billDao.findAllByUserAndPaidIsTrue(4);
        assertTrue(billList.size() == 1);
    }

    @Test
    public void testSetPaidTrue() throws Exception {
        Bill bill = billDao.findById(1);
        assertFalse(billDao.findById(1).isPaid());
        billDao.setPaidTrue(bill);
        assertTrue(billDao.findById(1).isPaid());
        bill.setPaid(false);
        billDao.edit(bill);
    }

    @Test
    public void testFindAllByPaidIsTrue()throws Exception {
        List<Bill> billList = billDao.findAllByPaidIsTrue();
        assertTrue(billList.size() == 2);
    }
}
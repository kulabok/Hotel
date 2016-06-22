package hotel.dao.impl;

import hotel.config.TestDataBaseConfig;
import hotel.dao.RequestServiceDao;
import hotel.dao.RoomServiceDao;
import hotel.dao.UserServiceDao;
import hotel.entity.Request;
import hotel.entity.Room;
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
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by kulabok on 15.06.2016.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
public class RoomServiceDaoImplTest {
    @Resource
    private EntityManagerFactory emf;
    private EntityManager em;
    @Resource
    private RoomServiceDao roomDao;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    @Test
    public void add() throws Exception {
        Room r = new Room();
        r.setNumber(404);
        r.setRoomclass(RoomClass.STANDARD);
        r.setAvailable(12);
        r.setCostPerPerson(1200);
        r.setPersonsMax(12);
        roomDao.add(r);
        assertTrue(roomDao.findByNumber(404)!=null);
        assertTrue(roomDao.findByNumber(404).getAvailable()==12);
        roomDao.delete(404);
    }

    @Test
    public void exist() throws Exception {
        assertTrue(roomDao.exist(108));
        assertTrue(roomDao.exist(208));
        assertTrue(roomDao.exist(105));
        assertTrue(roomDao.exist(204));
        assertTrue(roomDao.exist(308));
    }

    @Test
    public void delete() throws Exception {
        Room r = new Room();
        r.setNumber(404);
        r.setRoomclass(RoomClass.STANDARD);
        r.setAvailable(1);
        r.setCostPerPerson(120);
        r.setPersonsMax(1);
        roomDao.add(r);
        roomDao.delete(404);
        assertTrue(roomDao.findByNumber(404) == null);
    }

    @Test
    public void findByNumber() throws Exception {
        assertTrue(roomDao.findByNumber(303).getNumber() == 303);
        assertTrue(roomDao.findByNumber(102).getNumber() == 102);
        assertTrue(roomDao.findByNumber(205).getNumber() == 205);
    }

    @Test
    public void edit() throws Exception {
        Room r = new Room();
        r.setNumber(521);
        r.setRoomclass(RoomClass.STANDARD);
        r.setPersonsMax(3);
        r.setAvailable(3);
        r.setCostPerPerson(555);
        roomDao.add(r);
        Room room = roomDao.findByNumber(521);
        room.setCostPerPerson(333);
        roomDao.edit(room);
        assertTrue(roomDao.findByNumber(521).getCostPerPerson() == 333);
        assertFalse(roomDao.findByNumber(521).getCostPerPerson() == 555);
        roomDao.delete(521);
    }

    @Test
    public void findAll() throws Exception {
        List<Room> roomsList = roomDao.findAll();
        assertTrue(roomsList.size()>10);
    }

    @Test
    public void findAllByRoomclassAndAvailable() throws Exception {
        List<Room> roomsList = roomDao.findAllByRoomclassAndAvailable(RoomClass.LUX, 2);
        assertTrue(roomsList.size() == 6);
    }
}
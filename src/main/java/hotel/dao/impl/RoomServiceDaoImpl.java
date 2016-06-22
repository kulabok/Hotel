package hotel.dao.impl;

import hotel.dao.RoomServiceDao;
import hotel.dao.repository.BillRepository;
import hotel.dao.repository.RequestRepository;
import hotel.dao.repository.RoomRepository;
import hotel.entity.Bill;
import hotel.entity.Request;
import hotel.entity.Room;
import hotel.entity.enums.RoomClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class RoomServiceDaoImpl implements RoomServiceDao {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room add(Room room) {
        Room savedRoom = roomRepository.saveAndFlush(room);
        return savedRoom;
    }

    @Override
    public boolean exist(int number) {
        return roomRepository.exists(number);
    }

    @Override
    public void delete(int id) {
        roomRepository.delete(id);
    }

    @Override
    public Room findByNumber(int number) {
        return roomRepository.findOne(number);
    }

    @Override
    public Room edit(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findAllByRoomclassAndAvailable(RoomClass roomclass, int available) {
        return roomRepository.findAllByRoomclassAndAvailable(roomclass, available);
    }
}

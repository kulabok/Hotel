package hotel.dao;



import hotel.entity.Room;
import hotel.entity.enums.RoomClass;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface RoomServiceDao {
    Room add(Room room);
    boolean exist(int number);
    void delete(int id);
    Room findByNumber(int number);
    Room edit(Room room);
    List<Room> findAll();

    List<Room> findAllByRoomclassAndAvailable(RoomClass roomclass, int available);
}

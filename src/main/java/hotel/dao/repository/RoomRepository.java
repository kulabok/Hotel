package hotel.dao.repository;

import hotel.entity.Room;
import hotel.entity.enums.RoomClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE r.roomclass = :roomclass AND r.available >= :available")
    List<Room> findAllByRoomclassAndAvailable(@Param("roomclass") RoomClass roomclass, @Param("available") int available);
}

package hotel.dao.repository;

import hotel.entity.Request;
import hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query("select r from Request r where r.user.id = :id and r.processed = false")
    List<Request> findAllByUserAndProcessedIsFalse(@Param("id") int id);

    @Query("select r from Request r where r.user.id = :id and r.processed = true")
    List<Request> findAllByUserAndProcessedIsTrue(@Param("id")int id);

    List<Request> findAllByProcessedIsFalse();

    @Transactional
    @Modifying
    @Query("update Request r set r.processed = true where r.id = :id")
    void updateProcessedToTrue(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("update Request r set r.processed = false where r.id = :id")
    void updateProcessedToFalse(@Param("id") int id);

}

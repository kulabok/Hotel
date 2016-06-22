package hotel.dao;

import hotel.entity.Request;
import hotel.entity.User;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface RequestServiceDao {
    boolean exist(int id);
    Request add(Request request);
    void delete(int id);
    Request findById(int id);
    Request edit(Request request);
    List<Request> findAll();

    List<Request> findAllByUserAndProcessedIsFalse(int id);

    List<Request> findAllByUserAndProcessedIsTrue(int id);

    List<Request> findAllByProcessedIsFalse();

    void updateProcessedToTrue(int id);

    void updateProcessedToFalse(int id);
}

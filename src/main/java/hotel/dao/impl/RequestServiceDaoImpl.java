package hotel.dao.impl;

import hotel.dao.RequestServiceDao;
import hotel.dao.repository.RequestRepository;
import hotel.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class RequestServiceDaoImpl implements RequestServiceDao {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public boolean exist(int id) {
        return requestRepository.exists(id);
    }

    @Override
    public Request add(Request request) {
        Request savedRequest = requestRepository.saveAndFlush(request);
        return savedRequest;
    }

    @Override
    public void delete(int id) {
        requestRepository.delete(id);
    }

    @Override
    public Request findById(int id) {
        return requestRepository.findOne(id);
    }

    @Override
    public Request edit(Request request) {
        return requestRepository.saveAndFlush(request);
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> findAllByUserAndProcessedIsFalse(int id) {
        return requestRepository.findAllByUserAndProcessedIsFalse(id);
    }

    @Override
    public List<Request> findAllByUserAndProcessedIsTrue(int id) {
        return requestRepository.findAllByUserAndProcessedIsTrue(id);
    }

    @Override
    public List<Request> findAllByProcessedIsFalse() {
        return requestRepository.findAllByProcessedIsFalse();
    }

    @Override
    public void updateProcessedToTrue(int id) {
        requestRepository.updateProcessedToTrue(id);
    }

    @Override
    public void updateProcessedToFalse(int id) {
        requestRepository.updateProcessedToFalse(id);
    }
}

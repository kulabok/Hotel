package hotel.dao.impl;

import hotel.dao.BillServiceDao;
import hotel.dao.repository.BillRepository;
import hotel.dao.repository.RequestRepository;
import hotel.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
@Service
public class BillServiceDaoImpl implements BillServiceDao {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public boolean exist(int id) {
        return billRepository.exists(id);
    }

    @Override
    public Bill add(Bill bill) {
        Bill savedBill = billRepository.saveAndFlush(bill);
        return savedBill;
    }

    @Override
    public void delete(int id) {
        billRepository.delete(id);
    }

    @Override
    public Bill findById(int id) {
        return billRepository.findOne(id);
    }

    @Override
    public Bill edit(Bill bill) {
        return billRepository.saveAndFlush(bill);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> findAllByUserAndPaidIsFalse(int id) {
       return billRepository.findAllByRequestAndPaidIsFalse(id);
    }

    @Override
    public List<Bill> findAllByUserAndPaidIsTrue(int id) {
        return billRepository.findAllByRequestAndPaidIsTrue(id);
    }

    @Override
    public void setPaidTrue(Bill bill) {
        billRepository.setPaidTrue(bill.getId());
    }

    @Override
    public List<Bill> findAllByPaidIsTrue() {
        return billRepository.findAllByPaidIsTrue();
    }
}

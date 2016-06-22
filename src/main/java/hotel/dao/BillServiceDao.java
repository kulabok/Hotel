package hotel.dao;

import hotel.entity.Bill;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface BillServiceDao {
    boolean exist(int id);
    Bill add(Bill bill);
    void delete(int id);
    Bill findById(int id);
    Bill edit(Bill bill);
    List<Bill> findAll();

    List<Bill> findAllByUserAndPaidIsFalse(int id);

    List<Bill> findAllByUserAndPaidIsTrue(int id);

    void setPaidTrue(Bill bill);

    List<Bill> findAllByPaidIsTrue();
}
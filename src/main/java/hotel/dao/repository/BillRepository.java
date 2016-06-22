package hotel.dao.repository;

import hotel.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kulabok on 06.05.2016.
 */
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("select b from Bill b where b.request.user.id = :id and b.paid = false")
    List<Bill> findAllByRequestAndPaidIsFalse(@Param("id") int id);

    @Query("select b from Bill b where b.request.user.id = :id and b.paid = true")
    List<Bill> findAllByRequestAndPaidIsTrue(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("update Bill b set b.paid = true where b.id = :id")
    void setPaidTrue(@Param("id") int id);

    List<Bill> findAllByPaidIsTrue();
}

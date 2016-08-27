package hotel.dao.repository;

import hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.login = :login and u.password = :password")
    User exist(@Param("login") String login, @Param("password") String password);

    User findByLoginAndPassword(String login, String password);

    /*@Query("select u from User u where u.login = :login and u.password = :password")
    User isExist(@Param("login") String login, @Param("password") String password);*/
}

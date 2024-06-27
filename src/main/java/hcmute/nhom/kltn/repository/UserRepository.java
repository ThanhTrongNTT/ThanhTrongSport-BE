package hcmute.nhom.kltn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import hcmute.nhom.kltn.model.User;

/**
 * Class UserRepository.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface UserRepository extends AbstractRepository<User, String> {
    @Query(value = "SELECT * FROM t_user WHERE user_name = :userName", nativeQuery = true)
    User findByUsername(@Param("userName") String username);

    @Query(value = "SELECT * FROM t_user WHERE email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM t_user WHERE email LIKE :keyword OR user_name LIKE :keyword", nativeQuery = true)
    List<User> searchUser(@Param("keyword") String keyword);
}

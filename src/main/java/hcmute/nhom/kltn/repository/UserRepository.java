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
    @Query(value = "SELECT * FROM T_USER WHERE USER_NAME = :userName", nativeQuery = true)
    User findByUsername(@Param("userName") String username);

    @Query(value = "SELECT * FROM T_USER WHERE EMAIL = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM T_USER WHERE EMAIL LIKE :keyword OR USER_NAME LIKE :keyword", nativeQuery = true)
    List<User> searchUser(@Param("keyword") String keyword);
}

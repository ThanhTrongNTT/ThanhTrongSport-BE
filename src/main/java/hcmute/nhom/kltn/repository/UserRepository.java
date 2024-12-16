package hcmute.nhom.kltn.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
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
    @Query(value = "SELECT * FROM t_user WHERE user_name = :userName AND removal_flag = 0", nativeQuery = true)
    User findByUsername(@Param("userName") String username);

    //@Query(value = "SELECT * FROM t_user WHERE email = :email", nativeQuery = true)
    @EntityGraph(value = "User.detail", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.removalFlag = false")
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM t_user WHERE email LIKE :keyword OR user_name LIKE :keyword", nativeQuery = true)
    List<User> searchUser(@Param("keyword") String keyword);

    @Query(value = "SELECT u FROM User as u WHERE u.email = :email AND u.providerId = :providerId AND u.removalFlag = false")
    Optional<User> findByEmailAndProviderId(@Param("email") String email, @Param("providerId") String providerId);

    @Modifying
    @Query(value = "DELETE FROM t_role_to_user ur "
            + "WHERE ur.user_id = :userId ", nativeQuery = true)
    void deleteRoleUserByUserId(@Param("userId") String userId);

    @Modifying
    @Query(value = "DELETE FROM t_orders o "
            + "WHERE o.user_id = :userId ", nativeQuery = true)
    void deleteOrderUserByUserId(@Param("userId") String userId);

    @Modifying
    @Query(value = "DELETE FROM t_order_item oi"
            + " WHERE oi.order_id IN (SELECT o.id FROM t_orders o WHERE o.user_id = :userId)", nativeQuery = true)
    void deleteOrderItemByUserId(@Param("userId") String userId);

    @Query(value = "SELECT COUNT(*) FROM t_orders o WHERE removal_flag = false", nativeQuery = true)
    Integer countOrder();

    @Query(value = "SELECT SUM(total) FROM t_orders o WHERE removal_flag = false", nativeQuery = true)
    Double sumTotalPrice();
}

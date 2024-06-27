package hcmute.nhom.kltn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import hcmute.nhom.kltn.model.Order;

/**
 * Class OrderRepository.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface OrderRepository extends AbstractRepository<Order, String> {

    @Query(value = "SELECT * FROM t_order o INNER JOIN t_user u ON o.user_id = u.id WHERE u.email = :email",
            nativeQuery = true)
    List<Order> getOrderByUser(@PathVariable("email") String email);
}

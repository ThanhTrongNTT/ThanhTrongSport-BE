package hcmute.nhom.kltn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import hcmute.nhom.kltn.model.Cart;

/**
 * Class CartRepository.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface CartRepository extends AbstractRepository<Cart, String> {

    @Query(value = "SELECT * FROM t_cart c INNER JOIN t_user u ON c.user_id = u.id WHERE u.email = :email AND c.removal_flag = 0",
            nativeQuery = true)
    Cart getCartByUser(@PathVariable("email") String email);
}

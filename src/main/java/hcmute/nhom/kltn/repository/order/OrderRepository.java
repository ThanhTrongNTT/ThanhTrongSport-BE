package hcmute.nhom.kltn.repository.order;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import hcmute.nhom.kltn.model.order.Order;
import hcmute.nhom.kltn.repository.AbstractRepository;

/**
 * Class OrderRepository.
 *
 * @author: ThanhTrong
 **/
public interface OrderRepository extends AbstractRepository<Order, String> {

    @Query(value = "SELECT o FROM Order o WHERE o.user.email = :email and o.removalFlag = false")
    List<Order> getOrderByUser(@Param("email") String email);

    @Query(value = "SELECT o FROM Order o WHERE o.removalFlag = false")
    Page<Order> getAllOrder(Pageable pageable);

    @Query(value = "SELECT o FROM Order o WHERE o.removalFlag = false")
    List<Order> getOrderList();

    @Query(value = "SELECT COUNT(o) FROM Order o WHERE o.removalFlag = false")
    Long countOrder();

    @Query(value = "SELECT SUM(o.total) FROM Order o WHERE o.removalFlag = false")
    Double sumTotalPrice();
}

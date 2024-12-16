package hcmute.nhom.kltn.repository.order;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import hcmute.nhom.kltn.model.product.Coupon;
import hcmute.nhom.kltn.repository.AbstractRepository;

/**
 * Class CouponRepository.
 *
 * @author: ThanhTrong
 **/
public interface CouponRepository extends AbstractRepository<Coupon, String> {

    @Query("SELECT c FROM Coupon c WHERE c.removalFlag = false")
    Page<Coupon> getAllCoupon(Pageable pageable);

    @Query("SELECT c FROM Coupon c WHERE c.removalFlag = false")
    List<Coupon> getAllCouponList();
}

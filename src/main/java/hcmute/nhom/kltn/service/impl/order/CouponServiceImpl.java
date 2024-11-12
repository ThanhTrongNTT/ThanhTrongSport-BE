package hcmute.nhom.kltn.service.impl.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.CouponDTO;
import hcmute.nhom.kltn.mapper.order.CouponMapper;
import hcmute.nhom.kltn.model.product.Coupon;
import hcmute.nhom.kltn.repository.order.CouponRepository;
import hcmute.nhom.kltn.service.order.CouponService;
import hcmute.nhom.kltn.service.impl.AbstractServiceImpl;

/**
 * Class CouponServiceImpl.
 *
 * @author: ThanhTrong
 **/
@Service
@RequiredArgsConstructor
public class CouponServiceImpl
        extends AbstractServiceImpl<CouponRepository, CouponMapper, CouponDTO, Coupon>
        implements CouponService {
}

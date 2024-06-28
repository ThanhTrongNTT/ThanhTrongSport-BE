package hcmute.nhom.kltn.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.CartDetailDTO;
import hcmute.nhom.kltn.mapper.CartDetailMapper;
import hcmute.nhom.kltn.model.CartDetail;
import hcmute.nhom.kltn.repository.CartDetailRepository;
import hcmute.nhom.kltn.service.CartDetailService;

/**
 * Class CartDetailServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id: CartDetailService
 * @version: 1.0
 **/
@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl
        extends AbstractServiceImpl<CartDetailRepository, CartDetailMapper, CartDetailDTO, CartDetail>
        implements CartDetailService {
    private static final Logger logger = LoggerFactory.getLogger(CartDetailServiceImpl.class);
    private final String BL_NO = "CartDetailService";
    private final CartDetailRepository cartDetailRepository;

    @Override
    public CartDetailRepository getRepository() {
        return cartDetailRepository;
    }

    @Override
    public CartDetailMapper getMapper() {
        return CartDetailMapper.INSTANCE;
    }
}

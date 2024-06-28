package hcmute.nhom.kltn.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.OrderDetailDTO;
import hcmute.nhom.kltn.mapper.OrderDetailMapper;
import hcmute.nhom.kltn.model.OrderDetail;
import hcmute.nhom.kltn.repository.OrderDetailRepository;
import hcmute.nhom.kltn.service.OrderDetailService;

/**
 * Class OrderDetailServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl
        extends AbstractServiceImpl<OrderDetailRepository, OrderDetailMapper, OrderDetailDTO, OrderDetail>
        implements OrderDetailService {
    private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);
    private final String BL_NO = "OrderDetailService";
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailRepository getRepository() {
        return orderDetailRepository;
    }

    @Override
    public OrderDetailMapper getMapper() {
        return OrderDetailMapper.INSTANCE;
    }
}

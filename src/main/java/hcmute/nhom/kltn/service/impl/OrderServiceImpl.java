package hcmute.nhom.kltn.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.OrderDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.OrderMapper;
import hcmute.nhom.kltn.model.Order;
import hcmute.nhom.kltn.repository.OrderRepository;
import hcmute.nhom.kltn.service.OrderService;
import hcmute.nhom.kltn.util.Utilities;

/**
 * Class OrderServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends AbstractServiceImpl<OrderRepository, OrderMapper, OrderDTO, Order>
        implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final String BL_NO = "OrderService";
    private final OrderRepository orderRepository;

    @Override
    public OrderRepository getRepository() {
        return orderRepository;
    }

    @Override
    public OrderMapper getMapper() {
        return OrderMapper.INSTANCE;
    }

    @Override
    public Page<OrderDTO> getOrderByUser(String email, int pageNo, int pageSize, String sortBy, String sortDir) {
        logger.info(getMessageStart(BL_NO, "getOrderByUser"));
        logger.debug(getMessageInputParam(BL_NO, "email", email));
        checkInputParamGetOrderByUser(email);
        try {
            List<Order> orders = getRepository().getOrderByUser(email);
            List<OrderDTO> orderDTOList = orders.stream().map(order -> getMapper().toDto(order, getCycleAvoidingMappingContext()))
                    .collect(Collectors.toList());
            Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,
                    Utilities.getPageRequest(pageNo, pageSize, sortBy, sortDir), orderDTOList.size());
            logger.debug(getMessageOutputParam(BL_NO, "orderDTOPage", orderDTOPage));
            logger.info(getMessageEnd(BL_NO, "getOrderByUser"));
            return orderDTOPage;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, "getOrderByUser"));
            throw new SystemErrorException(e.getMessage());
        }

    }

    private void checkInputParamGetOrderByUser(String email) {
        if (Objects.isNull(email)) {
            String message = "Email is required!";
            logger.error(message);
            throw new SystemErrorException(message);
        }
    }
}

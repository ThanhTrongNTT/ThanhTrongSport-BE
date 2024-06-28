package hcmute.nhom.kltn.service;

import org.springframework.data.domain.Page;
import hcmute.nhom.kltn.dto.OrderDTO;
import hcmute.nhom.kltn.model.Order;

/**
 * Class OrderService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface OrderService extends AbstractService<OrderDTO, Order> {
    Page<OrderDTO> getOrderByUser(String email, int pageNo, int pageSize, String sortBy, String sortDir);
}

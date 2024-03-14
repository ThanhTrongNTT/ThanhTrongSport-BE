package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.OrderDTO;
import hcmute.nhom.kltn.model.Order;

/**
 * Class OrderMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface OrderMapper extends AbstractMapper<OrderDTO, Order> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}

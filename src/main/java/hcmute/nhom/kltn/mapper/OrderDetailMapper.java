package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.OrderDetailDTO;
import hcmute.nhom.kltn.model.OrderDetail;

/**
 * Class OrderDetailMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface OrderDetailMapper extends AbstractMapper<OrderDetailDTO, OrderDetail> {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
}

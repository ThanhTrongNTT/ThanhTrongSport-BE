package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.CartDetailDTO;
import hcmute.nhom.kltn.model.CartDetail;

/**
 * Class CartDetailMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface CartDetailMapper extends AbstractMapper<CartDetailDTO, CartDetail> {
    CartDetailMapper INSTANCE = Mappers.getMapper(CartDetailMapper.class);
}

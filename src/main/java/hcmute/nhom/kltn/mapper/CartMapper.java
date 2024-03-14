package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.CartDTO;
import hcmute.nhom.kltn.mapper.helper.CycleAvoidingMappingContext;
import hcmute.nhom.kltn.model.Cart;

/**
 * Class CartMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface CartMapper extends AbstractMapper<CartDTO, Cart> {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
}

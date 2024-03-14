package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.model.Product;

/**
 * Class ProductMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface ProductMapper extends AbstractMapper<ProductDTO, Product> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}

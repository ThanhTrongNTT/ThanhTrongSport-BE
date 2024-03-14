package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.CategoryDTO;
import hcmute.nhom.kltn.model.Category;

/**
 * Class CategoryMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface CategoryMapper extends AbstractMapper<CategoryDTO, Category> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}

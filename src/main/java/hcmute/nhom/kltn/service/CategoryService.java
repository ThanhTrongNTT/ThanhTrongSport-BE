package hcmute.nhom.kltn.service;

import hcmute.nhom.kltn.dto.CategoryDTO;
import hcmute.nhom.kltn.model.Category;

/**
 * Class CategoryService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface CategoryService extends AbstractService<CategoryDTO, Category> {

    CategoryDTO updateCategory(String id, CategoryDTO categoryDTO);
}

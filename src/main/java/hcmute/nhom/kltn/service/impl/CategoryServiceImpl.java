package hcmute.nhom.kltn.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.CategoryDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.CategoryMapper;
import hcmute.nhom.kltn.model.Category;
import hcmute.nhom.kltn.repository.CategoryRepository;
import hcmute.nhom.kltn.service.CategoryService;

/**
 * Class CategoryServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends AbstractServiceImpl<CategoryRepository, CategoryMapper, CategoryDTO, Category>
        implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    private final String BL_NO = "CategoryService";
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryRepository getRepository() {
        return categoryRepository;
    }

    @Override
    public CategoryMapper getMapper() {
        return CategoryMapper.INSTANCE;
    }

    @Override
    public CategoryDTO updateCategory(String id, CategoryDTO categoryDTO) {
        String methodName = "updateCategory";
        logger.info(getMessageStart(BL_NO, methodName));
        logger.info(getMessageInputParam(BL_NO, "id", id));
        logger.info(getMessageInputParam(BL_NO, "categoryDTO", categoryDTO));
        try {
            CategoryDTO category = findById(id);
            if (category == null) {
                throw new SystemErrorException("Category not found");
            }
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setDescription(categoryDTO.getDescription());
            category = save(category);
            logger.debug(getMessageOutputParam(BL_NO, "category", category));
            logger.info(getMessageEnd(BL_NO, methodName));
            return category;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemErrorException("Update category failed");
        }
    }
}

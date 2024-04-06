package hcmute.nhom.kltn.controller.v1;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hcmute.nhom.kltn.common.payload.ApiResponse;
import hcmute.nhom.kltn.dto.CategoryDTO;
import hcmute.nhom.kltn.service.CategoryService;

/**
 * Class CategoryController.
 *
 * @author: ThanhTrong
 * @function_id: 
 * @version:
**/
@RestController
public class CategoryController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<Page<CategoryDTO>>> getAllCategories(
            HttpServletRequest request,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("sortDir") String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllCategories"));
        Page<CategoryDTO> categoryDTOS = categoryService.getPaging(pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllCategories"));
        return ResponseEntity.ok(new ApiResponse<>(true, categoryDTOS, "Get all categories successfully!"));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> searchCategory(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchCategory"));
        CategoryDTO categoryDTO = categoryService.findById(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchCategory"));
        return ResponseEntity.ok(new ApiResponse<>(true, categoryDTO, "Search category successfully!"));
    }

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<CategoryDTO>> createCategory(
            HttpServletRequest request,
            @RequestBody CategoryDTO categoryDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "createCategory"));
        CategoryDTO category = categoryService.save(categoryDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "createCategory"));
        return ResponseEntity.ok(new ApiResponse<>(true, category, "Create category successfully!"));
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<ApiResponse<CategoryDTO>> updateCategory(
            HttpServletRequest request,
            @PathVariable("id") String id,
            @RequestBody CategoryDTO categoryDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "updateCategory"));
        CategoryDTO category = categoryService.updateCategory(id, categoryDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "updateCategory"));
        return ResponseEntity.ok(new ApiResponse<>(true, category, "Update category successfully!"));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCategory(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "deleteCategory"));
        categoryService.delete(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "deleteCategory"));
        return ResponseEntity.ok(new ApiResponse<>(true, true, "Delete category successfully!"));
    }

}

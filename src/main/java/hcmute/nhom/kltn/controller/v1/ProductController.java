package hcmute.nhom.kltn.controller.v1;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.dto.ProductItemDTO;
import hcmute.nhom.kltn.service.ProductService;
import hcmute.nhom.kltn.util.Constants;

/**
 * Class ProductController.
 *
 * @author: ThanhTrong
 * @function_id: 
 * @version:
**/
@RestController
public class ProductController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/search-products")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> searchProducts(
            HttpServletRequest request,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchProduct"));
        Page<ProductDTO> productDTOPage = productService.searchProducts(keyword, pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchProduct"));
        return ResponseEntity.ok(
                ApiResponse.<Page<ProductDTO>>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productDTOPage)
                        .message("Search product successfully!")
                        .build());
    }

    @GetMapping("/products/search-by-name")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> searchProduct(
            HttpServletRequest request,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchProduct"));
        Page<ProductDTO> productDTOPage = productService.searchProduct(keyword, pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchProduct"));
        return ResponseEntity.ok(
                ApiResponse.<Page<ProductDTO>>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productDTOPage)
                        .message("Search product successfully!")
                        .build());
    }

    @GetMapping("/products/search-by-category")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> searchProductByCategory(
            HttpServletRequest request,
            @RequestParam(value = "categoryName", defaultValue = "") String categoryName,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchProductByCategory"));
        Page<ProductDTO> productDTOPage = productService.searchProductByCategory(categoryName, pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchProductByCategory"));
        return ResponseEntity.ok(
                ApiResponse.<Page<ProductDTO>>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productDTOPage)
                        .message("Search product by category successfully!")
                        .build());
    }

    @GetMapping("/products/search-by-price")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> searchProductByPrice(
            HttpServletRequest request,
            @RequestParam("minPrice") Long minPrice,
            @RequestParam("maxPrice") Long maxPrice,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchProductByPrice"));
        Page<ProductDTO> productDTOPage = productService.searchProductByPrice(minPrice, maxPrice, pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchProductByPrice"));
        return ResponseEntity.ok(
                ApiResponse.<Page<ProductDTO>>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productDTOPage)
                        .message("Search product by price successfully!")
                        .build());
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> getAllProduct(
            HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllProduct"));
        Page<ProductDTO> productDTOPage = productService.getPaging(pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllProduct"));
        return ResponseEntity.ok(
                ApiResponse.<Page<ProductDTO>>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productDTOPage)
                        .message("Get all product successfully!")
                        .build());
    }

    @GetMapping("/products/list")
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> getAllProductList(
            HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllProduct"));
        Page<ProductDTO> productDTOPage = productService.getList(pageNo,pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllProduct"));
        return ResponseEntity.ok(
                ApiResponse.<Page<ProductDTO>>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productDTOPage)
                        .message("Get all product successfully!")
                        .build());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getProductById(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getProductById"));
        ProductDTO productDTO = productService.findById(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getProductById"));
        return ResponseEntity.ok(
                ApiResponse.<ProductDTO>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productDTO)
                        .message("Get product by id successfully!")
                        .build());
    }

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<ProductDTO>> createProduct(
            HttpServletRequest request,
            @RequestBody ProductDTO productDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "createProduct"));
        ProductDTO product = productService.save(productDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "createProduct"));
        return ResponseEntity.ok(
                ApiResponse.<ProductDTO>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(product)
                        .message("Create product successfully!")
                        .build());
    }

    @PostMapping("/product/:productId/item")
    public ResponseEntity<ApiResponse<ProductItemDTO>> createProductItem(
            HttpServletRequest request,
            @RequestBody ProductItemDTO productItemDTO,
            @PathVariable("productId") String productId
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "createProductItem"));
        ProductItemDTO productItem = productService.saveProductItem(productId, productItemDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "createProductItem"));
        return ResponseEntity.ok(
                ApiResponse.<ProductItemDTO>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(productItem)
                        .message("Create product item successfully!")
                        .build());
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(
            HttpServletRequest request,
            @RequestBody ProductDTO productDTO,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "updateProduct"));
        ProductDTO product = productService.updateProduct(id, productDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "updateProduct"));
        return ResponseEntity.ok(
                ApiResponse.<ProductDTO>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .data(product)
                        .message("Update product successfully!")
                        .build());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "deleteProduct"));
        productService.delete(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "deleteProduct"));
        return ResponseEntity.ok(
                ApiResponse.<Boolean>builder()
                        .code(HttpStatus.OK.toString())
                        .result(true)
                        .message("Delete product successfully!")
                        .build());
    }

}

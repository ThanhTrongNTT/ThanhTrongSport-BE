package hcmute.nhom.kltn.service;

import java.util.List;
import org.springframework.data.domain.Page;
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.model.Product;

/**
 * Class ProductService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface ProductService extends AbstractService<ProductDTO, Product> {
    Page<ProductDTO> searchProduct(String keyword, int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDTO updateProduct(String id, ProductDTO productDTO);

    Page<ProductDTO> searchProductByCategory(String categoryName, int pageNo, int pageSize, String sortBy, String sortDir);

    Page<ProductDTO> getList(int pageNo, int pageSize, String sortBy, String sortDir);
}

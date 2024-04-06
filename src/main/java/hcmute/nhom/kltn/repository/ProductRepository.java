package hcmute.nhom.kltn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import hcmute.nhom.kltn.model.Product;

/**
 * Class ProductRepository.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface ProductRepository extends AbstractRepository<Product, String> {

    @Query(value = "SELECT p FROM Product p"
            + "WHERE p.PRODUCT_NAME LIKE %:keyword% AND REMOVAL_FLAG = 0", nativeQuery = true)
    List<Product> searchProduct(@Param("keyword") String keyword);
}

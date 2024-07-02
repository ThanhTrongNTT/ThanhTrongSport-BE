package hcmute.nhom.kltn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import hcmute.nhom.kltn.model.Product;

/**
 * Class ProductRepository.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface ProductRepository extends AbstractRepository<Product, String> {

    @Query(value = "SELECT * FROM t_product "
            + "WHERE product_name LIKE %:keyword% AND removal_flag = 0", nativeQuery = true)
    List<Product> searchProduct(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM t_product p JOIN t_category c ON c.id = p.category_id "
            + "WHERE p.removal_flag = 0 AND c.category_name = :categoryName AND p.product_name LIKE %:keyword%", nativeQuery = true)
    List<Product> searchProductByCategoryAndKeyword(@Param("categoryName") String categoryName, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM t_product p JOIN t_category c ON c.id = p.category_id "
            + "WHERE p.removal_flag = 0 AND c.category_name = :categoryName", nativeQuery = true)
    List<Product> searchProductByCategory(@Param("categoryName") String categoryName);

    @Query(value = "SELECT * FROM t_product WHERE price >= :minPrice AND price <= :maxPrice AND removal_flag = 0 ", nativeQuery = true)
    List<Product> searchProductByPrice(@Param("minPrice") Long minPice, @Param("maxPrice") Long maxPrice);
}

package hcmute.nhom.kltn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import hcmute.nhom.kltn.model.Size;

/**
 * Class SizeRepository.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface SizeRepository extends AbstractRepository<Size, String> {
    @Query(value = "SELECT * FROM t_product p INNER JOIN t_sizeSELECT s.name"
            + " FROM t_product p INNER JOIN t_size s ON p.size_id = s.id"
            + " WHERE p.product_name = :productName AND s.removal_flag =0", nativeQuery = true)
    List<Size> findAllByProductName(@Param("productName") String productName);
}

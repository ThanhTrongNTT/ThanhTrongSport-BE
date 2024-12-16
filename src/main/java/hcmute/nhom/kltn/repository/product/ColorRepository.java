package hcmute.nhom.kltn.repository.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import hcmute.nhom.kltn.model.product.Color;
import hcmute.nhom.kltn.repository.AbstractRepository;

/**
 * Class ColorRepository.
 *
 * @author: ThanhTrong
 **/
public interface ColorRepository extends AbstractRepository<Color, String> {

    @Query("SELECT c FROM Color c WHERE c.removalFlag = false")
    Page<Color> getAllColor(Pageable pageable);

    @Query("SELECT c FROM Color c WHERE c.removalFlag = false")
    List<Color> getAllList();
}

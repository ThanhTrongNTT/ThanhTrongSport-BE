package hcmute.nhom.kltn.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Class AbstractRepository.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@NoRepositoryBean
public interface AbstractRepository<E, ID extends Serializable> extends JpaRepository<E, ID> {
}

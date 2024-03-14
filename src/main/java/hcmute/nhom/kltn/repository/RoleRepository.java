package hcmute.nhom.kltn.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import hcmute.nhom.kltn.enums.RoleName;
import hcmute.nhom.kltn.model.Role;

/**
 * Class RoleRepository.
 *
 * @author: ThanhTrong
 **/
public interface RoleRepository extends AbstractRepository<Role, String> {

    @Query(value = "SELECT * FROM T_ROLE r WHERE r.ROLE_NAME = :name AND REMOVAL_FLAG = 0", nativeQuery = true)
    Optional<Role> findByName(RoleName name);
}

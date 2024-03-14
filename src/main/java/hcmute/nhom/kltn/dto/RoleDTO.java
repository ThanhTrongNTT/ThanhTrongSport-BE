package hcmute.nhom.kltn.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import hcmute.nhom.kltn.enums.RoleName;

/**
 * Class RoleDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends AbstractDTO {
    private String id;
    private RoleName roleName;
    private Boolean adminFlag;
    private Set<UserDTO> users;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "RoleDTO [id=" + id + ", roleName=" + roleName + ", adminFlag=" + adminFlag
                + ", removalFlag=" + removalFlag + "]";
    }
}

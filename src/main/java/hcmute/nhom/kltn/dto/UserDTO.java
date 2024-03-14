package hcmute.nhom.kltn.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import hcmute.nhom.kltn.model.Role;

/**
 * Class UserDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends AbstractDTO {
    private String id;
    private String userName;
    private String email;
    private String password;
    private Boolean activeFlag;
    private UserProfileDTO userProfile;
    private Set<RoleDTO> roles;
    private Boolean removalFlag;


    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", username=" + userName + ", email=" + email + ", password=" + password
                + ", userProfile=" + userProfile + ", removalFlag=" + removalFlag + "]";
    }
}

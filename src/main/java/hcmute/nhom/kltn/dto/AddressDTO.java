package hcmute.nhom.kltn.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import hcmute.nhom.kltn.model.User;

/**
 * Class AddressDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO extends AbstractDTO {
    private String id;
    private String address;
    private String ward;
    private String district;
    private String city;
    private String phone;
    private String email;
    private String lastName;
    private String firstName;
    private String isDefault;
    private UserDTO user;
}

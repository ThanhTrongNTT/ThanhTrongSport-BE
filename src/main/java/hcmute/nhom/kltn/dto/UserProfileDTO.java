package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import hcmute.nhom.kltn.model.MediaFile;

/**
 * Class UserProfileDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO extends AbstractDTO {
    private String id;
    private String firstName;
    private String lastName;
    private MediaFileDTO avatar;
    private Boolean removalFlag;

    private String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", removalFlag=" + removalFlag +
                '}';
    }
}

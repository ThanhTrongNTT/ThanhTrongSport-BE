package hcmute.nhom.kltn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class UserProfile.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "T_USER_PROFILE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends AbstractAuditModel {

    /**
     * UserProfile entity.
     */

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "AVATAR_ID", referencedColumnName = "ID")
    private MediaFile avatar;

    @Column(name = "REMOVAL_FLAG", nullable = false, length = 1)
    private Boolean removalFlag = false;

    private String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + id
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", avatar=" + avatar
                + ", removalFlag=" + removalFlag + "]";
    }
}

package hcmute.nhom.kltn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "t_user_profile")
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
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private MediaFile avatar;

//    @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
//    private User user;

    @Column(name = "removal_flag", nullable = false, length = 1)
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

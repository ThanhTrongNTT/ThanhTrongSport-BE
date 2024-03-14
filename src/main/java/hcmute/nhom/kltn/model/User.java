package hcmute.nhom.kltn.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class User.
 *
 * @author: ThanhTrong
 **/
@Entity
@Table(name = "T_USER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractAuditModel {

    /**
     * User entity.
     */

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ACTIVE_FLAG", length = 1)
    private Boolean activeFlag;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID")
    private UserProfile userProfile;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_ROLE_TO_USER",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    @Column(name = "REMOVAL_FLAG", length = 1)
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "User [id=" + id
                + ", userName=" + userName
                + ", email=" + email
                + ", password=" + password
                + ", activeFlag=" + activeFlag
                + ", removalFlag=" + removalFlag + "]";
    }
}

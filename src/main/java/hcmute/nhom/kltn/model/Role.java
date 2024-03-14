package hcmute.nhom.kltn.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import hcmute.nhom.kltn.enums.RoleName;

/**
 * Class Role.
 *
 * @author: ThanhTrong
 **/
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractAuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "ROLE_NAME")
    private RoleName roleName;

    @Column(name = "ADMIN_FLAG")
    private Boolean adminFlag;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Column(name = "REMOVAL_FLAG", nullable = false, length = 1)
    private Boolean removalFlag = false;

    @Override
    public String toString() {
        return "Role [id=" + id
                + ", roleName=" + roleName
                + ", adminFlag=" + adminFlag
                + ", removalFlag=" + removalFlag + "]";
    }
}

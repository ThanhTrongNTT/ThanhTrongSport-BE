package hcmute.nhom.kltn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class Category.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "T_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractAuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "REMOVAL_FLAG", nullable = false, length = 1)
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "Category [id=" + id
                + ", categoryName=" + categoryName
                + ", description=" + description
                + ", removalFlag=" + removalFlag + "]";
    }
}

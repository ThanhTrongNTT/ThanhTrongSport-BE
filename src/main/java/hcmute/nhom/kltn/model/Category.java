package hcmute.nhom.kltn.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "t_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractAuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "category_name", unique = true, nullable = false)
    private String categoryName;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Product> product;
    @Column(name = "removal_flag", nullable = false, length = 1)
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "Category [id=" + id
                + ", categoryName=" + categoryName
                + ", description=" + description
                + ", removalFlag=" + removalFlag + "]";
    }
}

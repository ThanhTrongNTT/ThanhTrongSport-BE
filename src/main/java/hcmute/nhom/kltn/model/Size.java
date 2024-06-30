package hcmute.nhom.kltn.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class Size.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "t_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Size extends AbstractAuditModel{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "value")
    private String value;
    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
//    private List<Product> product;

    @Column(name = "removal_flag", nullable = false, length = 1)
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "Size{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", removalFlag=" + removalFlag +
                '}';
    }
}

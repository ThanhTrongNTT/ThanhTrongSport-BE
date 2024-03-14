package hcmute.nhom.kltn.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class Cart.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "T_CART")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends AbstractAuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    @Column(name = "REMOVAL_FLAG", nullable = false, length = 1)
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "Cart [id=" + id
                + ", removalFlag=" + removalFlag + "]";
    }
}

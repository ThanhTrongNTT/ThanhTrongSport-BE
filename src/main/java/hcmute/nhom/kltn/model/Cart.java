package hcmute.nhom.kltn.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * Class Cart.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "t_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends AbstractAuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetail> cartDetails;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cart")
    private OrderDetail orderDetail;

    @Column(name = "total", nullable = false)
    private Long total;

    @Column(name = "removal_flag", nullable = false, length = 1)
    private Boolean removalFlag;

    public Long getTotal() {
        Long total = 0L;
        for (CartDetail cartDetail : cartDetails) {
            total += cartDetail.getProduct().getPrice() * cartDetail.getQuantity();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Cart [id=" + id
                + ", removalFlag=" + removalFlag + "]";
    }
}

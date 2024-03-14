package hcmute.nhom.kltn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class CartDetail.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "T_CART_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail extends AbstractAuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "QUANTITY")
    private Integer quantity;

    private Boolean removalFlag;

    @Override
    public String toString() {
        return "CartDetail [id=" + id
                + ", removalFlag=" + removalFlag + "]";
    }
}

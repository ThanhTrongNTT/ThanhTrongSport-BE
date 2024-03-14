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
 * Class OrderDetail.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "T_ORDER_DETAIL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail extends AbstractAuditModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "REMOVAL_FLAG", nullable = false, length = 1)
    private Boolean removalFlag;


    @Override
    public String toString() {
        return "OrderDetail [id=" + id
                + ", removalFlag=" + removalFlag + "]";
    }
}

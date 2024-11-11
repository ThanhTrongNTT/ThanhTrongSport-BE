package hcmute.nhom.kltn.model.order;

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import hcmute.nhom.kltn.model.AbstractAuditModel;
import hcmute.nhom.kltn.model.User;
import hcmute.nhom.kltn.model.Address;
import hcmute.nhom.kltn.model.product.Price;

/**
 * Class Order.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AbstractAuditModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "products_count")
    private int productsCount;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;
    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "subtotal_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "subtotal_currency")),
            @AttributeOverride(name = "unit", column = @Column(name = "subtotal_unit"))
    })
    private Price subtotal;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "tax_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "tax_currency")),
            @AttributeOverride(name = "unit", column = @Column(name = "tax_unit"))
    })
    private Price tax;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "total_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "total_currency")),
            @AttributeOverride(name = "unit", column = @Column(name = "total_unit"))
    })
    private Price total;
    @Column(name = "status")
    private String status;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "is_paid")
    private Boolean isPaid;
}

package hcmute.nhom.kltn.model.product;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import hcmute.nhom.kltn.model.AbstractAuditModel;
import hcmute.nhom.kltn.model.Image;

/**
 * Class ProductItem.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "t_product_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItem extends AbstractAuditModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "color_code")),
            @AttributeOverride(name = "displayCode", column = @Column(name = "color_display_code")),
            @AttributeOverride(name = "name", column = @Column(name = "color_name"))
    })
    private Color color;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "size_code")),
            @AttributeOverride(name = "displayCode", column = @Column(name = "size_display_code")),
            @AttributeOverride(name = "name", column = @Column(name = "size_name"))
    })
    private Size size;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "statusCode", column = @Column(name = "status_code")),
            @AttributeOverride(name = "quantity", column = @Column(name = "quantity")),
    })
    private Stock stock;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_id")
    private Sales sales;
    @Embedded
    private Prices price;
    @OneToOne
    @JoinColumn(name = "main_image_id")
    private Image mainImage;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

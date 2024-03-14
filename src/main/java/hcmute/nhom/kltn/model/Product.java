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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Class Product.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Entity
@Table(name = "T_PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractAuditModel {
    /**
     * Product entity.
     */

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MediaFile> images;

    @Column(name = "PRODUCT_CATEGORY")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> productCategory;

    @Column(name = "REMOVAL_FLAG", nullable = false, length = 1)
    private Boolean removalFlag = false;

    @Override
    public String toString() {
        return "Product [id=" + id
                + ", productName=" + productName
                + ", description=" + description
                + ", price=" + price
                + ", quantity=" + quantity
                + ", removalFlag=" + removalFlag + "]";
    }
}

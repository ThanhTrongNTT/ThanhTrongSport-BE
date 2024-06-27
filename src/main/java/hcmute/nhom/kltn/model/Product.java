package hcmute.nhom.kltn.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "t_product")
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
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private Size size;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<MediaFile> images;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category productCategory;

    @OneToOne(mappedBy = "product")
    private CartDetail cartDetail;

    @Column(name = "removal_flag", nullable = false, length = 1)
    private Boolean removalFlag = false;

    @Override
    public String toString() {
        return "Product [id=" + id
                + ", productName=" + productName
                + ", description=" + description
                + ", price=" + price
                + ", size=" + size
                + ", images=" + images
                + ", quantity=" + quantity
                + ", removalFlag=" + removalFlag + "]";
    }
}

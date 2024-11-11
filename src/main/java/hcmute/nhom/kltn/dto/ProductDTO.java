package hcmute.nhom.kltn.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import hcmute.nhom.kltn.model.Image;
import hcmute.nhom.kltn.model.product.Category;
import hcmute.nhom.kltn.model.product.Price;
import hcmute.nhom.kltn.model.product.ProductItem;
import hcmute.nhom.kltn.model.product.Rating;

/**
 * Class ProductDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends AbstractDTO {
    private String id;
    private String freeInformation;
    private String longDescription;
    private String washingInformation;
    private String productName;
    private String slug;
    private Price price;
    private CategoryDTO gender;
    private CategoryDTO category;
    private List<ProductItemDTO> listProductItem;
    private List<ImageDTO> subImages = new ArrayList<>();
    private List<RatingDTO> ratings;
    private Boolean removalFlag;

}

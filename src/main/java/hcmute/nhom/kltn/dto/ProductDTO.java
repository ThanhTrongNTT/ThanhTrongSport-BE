package hcmute.nhom.kltn.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String productName;
    private String description;
    private Long price;
    private Integer quantity;
    private List<MediaFileDTO> images;
    private List<CategoryDTO> productCategory;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "ProductDTO [id=" + id
                + ", productName=" + productName
                + ", description=" + description
                + ", images=" + images
                + ", price=" + price
                + ", quantity=" + quantity
                + ", productCategory=" + productCategory
                + ", removalFlag=" + removalFlag + "]";
    }
}

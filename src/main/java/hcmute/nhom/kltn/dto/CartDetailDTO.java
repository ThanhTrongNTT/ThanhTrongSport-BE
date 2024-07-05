package hcmute.nhom.kltn.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class CartDetailDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDTO extends AbstractDTO {
    private String id;
    private ProductDTO product;
    private Integer quantity;
    @JsonIgnore
    private CartDTO cart;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "CartDetailDTO [id=" + id
                + ", product=" + product
                + ", removalFlag=" + removalFlag + "]";
    }
}

package hcmute.nhom.kltn.dto;

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
    private CartDTO cart;
    private UserDTO user;
    private ProductDTO product;
    private Integer quantity;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "CartDetailDTO [id=" + id
                + ", cart=" + cart
                + ", user=" + user
                + ", product=" + product
                + ", quantity=" + quantity
                + ", removalFlag=" + removalFlag + "]";
    }
}

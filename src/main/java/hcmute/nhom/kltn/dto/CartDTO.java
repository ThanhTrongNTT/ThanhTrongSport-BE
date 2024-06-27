package hcmute.nhom.kltn.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import hcmute.nhom.kltn.model.CartDetail;

/**
 * Class CartDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO extends AbstractDTO {
    private String id;
    private UserDTO user;
    private List<CartDetailDTO> cartDetails;
    private Boolean removalFlag;
    private Long total;

    public Long getTotal() {
        Long total = 0L;
        for (CartDetailDTO cartDetail : cartDetails) {
            total += cartDetail.getProduct().getPrice() * cartDetail.getQuantity();
        }
        return total;
    }

    @Override
    public String toString() {
        return "CartDTO [id=" + id
                + ", removalFlag=" + removalFlag + "]";
    }
}

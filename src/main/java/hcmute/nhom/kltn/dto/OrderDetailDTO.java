package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class OrderDetailDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO extends AbstractDTO {
    private String id;
    private CartDTO cart;
    private OrderDTO order;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "OrderDetailDTO [id=" + id
                + ", cart=" + cart
                + ", order=" + order
                + ", removalFlag=" + removalFlag + "]";
    }
}

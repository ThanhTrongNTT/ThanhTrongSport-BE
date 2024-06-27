package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class OrderDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends AbstractDTO {
    private String id;
    private UserDTO user;
    private OrderDetailDTO orderDetail;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "OrderDTO [id=" + id
                + ", orderDetail=" + orderDetail
                + ", removalFlag=" + removalFlag + "]";
    }
}

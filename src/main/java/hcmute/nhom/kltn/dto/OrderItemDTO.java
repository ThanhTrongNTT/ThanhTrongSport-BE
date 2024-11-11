package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import hcmute.nhom.kltn.model.product.Price;

/**
 * Class OrderItemDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO extends AbstractDTO {
    private String id;
    private int quantity;
    private ProductItemDTO product;
    private OrderDTO order;
    private Price subTotal;

}

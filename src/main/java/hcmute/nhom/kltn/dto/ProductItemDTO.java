package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import hcmute.nhom.kltn.model.product.Color;
import hcmute.nhom.kltn.model.product.Prices;

/**
 * Class ProductItemDTo.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemDTO extends AbstractDTO {
    private String id;
    private Color color;
    private SizeDTO size;
    private Prices price;
    private StockDTO stock;
    private SalesDTO sales;
    private ImageDTO mainImage;
    private ProductDTO product;
}

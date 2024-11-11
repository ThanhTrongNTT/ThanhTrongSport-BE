package hcmute.nhom.kltn.model.product;

import java.math.BigDecimal;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class Price.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Price {
    private String currency;
    private String unit;
    private BigDecimal value;
}

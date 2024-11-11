package hcmute.nhom.kltn.model.product;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
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
public class Prices {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "base_currency")),
            @AttributeOverride(name = "unit", column = @Column(name = "base_unit")),
            @AttributeOverride(name = "value", column = @Column(name = "base_value"))
    })
    private Price base;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "promo_currency")),
            @AttributeOverride(name = "unit", column = @Column(name = "promo_unit")),
            @AttributeOverride(name = "value", column = @Column(name = "promo_value"))
    })
    private Price promo;
}

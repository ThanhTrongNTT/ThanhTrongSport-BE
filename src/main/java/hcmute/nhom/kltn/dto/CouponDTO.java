package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class CouponDTO.
 *
 * @author: ThanhTrong
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO extends AbstractDTO {
    private String id;
    private String code;
    private double discount;
    private String description;
    private String startDate;
    private String endDate;
    private Boolean removalFlag;
}

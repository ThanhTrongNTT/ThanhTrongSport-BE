package hcmute.nhom.kltn.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Class AdminInformationDTO.
 *
 * @author: ThanhTrong
 **/
@Getter
@Setter
@Builder
public class AdminInformationDTO {
    private Double totalMoney;
    private Integer totalOrders;
    private Integer totalClient;
    private Integer totalProduct;
}

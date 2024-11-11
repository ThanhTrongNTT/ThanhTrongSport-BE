package hcmute.nhom.kltn.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class SalesDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDTO extends AbstractDTO {
    private String id;
    private String name;
    private String description;
    private String code;
    private double discount;
    private Date startDate;
    private Date endDate;
}

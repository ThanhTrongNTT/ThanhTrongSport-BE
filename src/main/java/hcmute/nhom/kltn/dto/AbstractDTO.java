package hcmute.nhom.kltn.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class AbstractDTO.
 *
 * @author: ThanhTrong
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractDTO extends AbstractNonAuditDTO {

    protected String createdBy;
    protected Date createdDate;
    protected String modifiedBy;
    protected Date modifiedDate;
}

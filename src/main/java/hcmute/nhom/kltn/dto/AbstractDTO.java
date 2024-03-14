package hcmute.nhom.kltn.dto;

import java.io.Serializable;
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
public class AbstractDTO extends AbstractNonAuditDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String createdBy;
    protected Date createdDate;
    protected String modifiedBy;
    protected Date modifiedDate;
}

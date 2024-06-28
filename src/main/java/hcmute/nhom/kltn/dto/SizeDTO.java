package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class SizeDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO extends AbstractDTO {
    private String id;
    private String name;
    private String value;
    private String description;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "SizeDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                ", removalFlag=" + removalFlag +
                '}';
    }
}

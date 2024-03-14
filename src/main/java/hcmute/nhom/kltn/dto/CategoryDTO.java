package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class CategoryDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO extends AbstractDTO {
    private String id;
    private String categoryName;
    private String description;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "CategoryDTO [id=" + id
                + ", categoryName=" + categoryName
                + ", description=" + description
                + ", removalFlag=" + removalFlag + "]";
    }
}

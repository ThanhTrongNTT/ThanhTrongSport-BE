package hcmute.nhom.kltn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class MediaFileDTO.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaFileDTO extends AbstractDTO {
    private String id;
    private String fileName;
    private String fileType;
    private String url;
    private Boolean removalFlag;

    @Override
    public String toString() {
        return "MediaFileDTO{" +
                "id='" + id +
                ", fileName='" + fileName +
                ", fileType='" + fileType +
                ", url='" + url +
                ", removalFlag=" + removalFlag +
                '}';
    }
}

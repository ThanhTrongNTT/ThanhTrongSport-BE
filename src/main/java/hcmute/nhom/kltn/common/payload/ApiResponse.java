package hcmute.nhom.kltn.common.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class ApiResponse.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private Boolean result;
    private String message;
    private String code;
    private T data;
}

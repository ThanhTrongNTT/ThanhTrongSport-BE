package hcmute.nhom.kltn.exception;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import hcmute.nhom.kltn.common.payload.ApiResponse;

/**
 * Class GlobalExceptionHandler.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleNotFoundException(NotFoundException e) {
        return ApiResponse.builder()
                .data(null)
                .code(HttpStatus.NOT_FOUND.toString())
                .result(false)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleException(Exception e) {
        return ApiResponse.builder()
                .data(null)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .result(false)
                .message(e.getMessage())
                .build();
    }
}

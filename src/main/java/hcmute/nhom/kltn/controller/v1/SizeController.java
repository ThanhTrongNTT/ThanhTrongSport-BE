package hcmute.nhom.kltn.controller.v1;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hcmute.nhom.kltn.common.payload.ApiResponse;
import hcmute.nhom.kltn.dto.SizeDTO;
import hcmute.nhom.kltn.service.SizeService;
import hcmute.nhom.kltn.util.Constants;

/**
 * Class SizeController.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@RestController
public class SizeController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(SizeController.class);
    private final SizeService sizeService;

    public SizeController(final SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("/sizes")
    public ResponseEntity<ApiResponse<Page<SizeDTO>>> getAllSizes(
            HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllSizes"));
        Page<SizeDTO> sizeDTOS = sizeService.getPaging(pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllSizes"));
        return ResponseEntity.ok(new ApiResponse<>(true, sizeDTOS, "Get all sizes successfully!"));
    }

    @GetMapping("/sizes/list")
    public ResponseEntity<ApiResponse<List<SizeDTO>>> getAllSizes(
            HttpServletRequest request
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllSizes"));
        List<SizeDTO> sizeDTOS = sizeService.findAll();
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllSizes"));
        return ResponseEntity.ok(new ApiResponse<>(true, sizeDTOS, "Get list sizes successfully!"));
    }

    @GetMapping("/sizes/list/{productName}")
    public ResponseEntity<ApiResponse<List<SizeDTO>>> getAllSizesByProductName(
            HttpServletRequest request,
            @PathVariable(value = "productName") String productName
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllSizesByProductName"));
        List<SizeDTO> sizeDTOS = sizeService.findAllByProductName(productName);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllSizesByProductName"));
        return ResponseEntity.ok(new ApiResponse<>(true, sizeDTOS, "Get list sizes by product name successfully!"));
    }

    @GetMapping("/sizes/{id}")
    public ResponseEntity<ApiResponse<SizeDTO>> searchSize(
            HttpServletRequest request,
            @PathVariable(value = "id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchSize"));
        SizeDTO sizeDTO = sizeService.findById(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchSize"));
        return ResponseEntity.ok(new ApiResponse<>(true, sizeDTO, "Get size successfully!"));
    }

    @PostMapping("/size")
    public ResponseEntity<ApiResponse<SizeDTO>> createSize(
            HttpServletRequest request,
            @RequestBody SizeDTO sizeDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "createSize"));
        sizeDTO.setRemovalFlag(false);
        SizeDTO size = sizeService.save(sizeDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "createSize"));
        return ResponseEntity.ok(new ApiResponse<>(true, size, "Create size successfully!"));
    }

    @PutMapping("/size/{id}")
    public ResponseEntity<ApiResponse<SizeDTO>> updateSize(
            HttpServletRequest request,
            @PathVariable("id") String id,
            @RequestBody SizeDTO sizeDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "updateSize"));
        SizeDTO size = sizeService.updateSize(id, sizeDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "updateSize"));
        return ResponseEntity.ok(new ApiResponse<>(true, size, "Update size successfully!"));
    }

    @DeleteMapping("/size/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteSize(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "deleteSize"));
        sizeService.delete(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "deleteSize"));
        return ResponseEntity.ok(new ApiResponse<>(true, true, "Delete size successfully!"));
    }
}

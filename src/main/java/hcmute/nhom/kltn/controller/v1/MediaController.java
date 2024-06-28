package hcmute.nhom.kltn.controller.v1;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import hcmute.nhom.kltn.common.payload.ApiResponse;
import hcmute.nhom.kltn.dto.MediaFileDTO;
import hcmute.nhom.kltn.service.MediaFileService;

/**
 * Class MediaController.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@RestController
@RequiredArgsConstructor
public class MediaController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
    private final MediaFileService mediaFileService;

    @PostMapping("/media/upload")
    public ResponseEntity<ApiResponse<MediaFileDTO>> uploadFile(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file
    ){
        logger.info(getMessageStart(request.getRequestURL().toString(), "uploadFile"));
        MediaFileDTO mediaFileDTO = mediaFileService.uploadFile(file);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "uploadFile"));
        return ResponseEntity.ok(new ApiResponse<>(true, mediaFileDTO, "Upload file successfully!"));
    }

    @PostMapping("/media/upload-files")
    public ResponseEntity<ApiResponse<List<MediaFileDTO>>> uploadFiles(
            HttpServletRequest request,
            @RequestParam("files") List<MultipartFile> files
    ){
        logger.info(getMessageStart(request.getRequestURL().toString(), "uploadFiles"));
        List<MediaFileDTO> mediaFileDTOs = mediaFileService.uploadFiles(files);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "uploadFiles"));
        return ResponseEntity.ok(new ApiResponse<>(true, mediaFileDTOs, "Upload files successfully!"));
    }
}

package hcmute.nhom.kltn.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import hcmute.nhom.kltn.dto.MediaFileDTO;
import hcmute.nhom.kltn.model.MediaFile;

/**
 * Class MediaFileService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface MediaFileService extends AbstractService<MediaFileDTO, MediaFile> {

    MediaFileDTO uploadFile(MultipartFile file);

    List<MediaFileDTO> uploadFiles(List<MultipartFile> files);

    MediaFileDTO findByFileName(String fileName);

    MediaFileDTO getDefaultAvatar();
}

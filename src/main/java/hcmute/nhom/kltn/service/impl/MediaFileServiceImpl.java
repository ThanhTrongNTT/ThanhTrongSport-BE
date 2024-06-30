package hcmute.nhom.kltn.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import hcmute.nhom.kltn.dto.MediaFileDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.MediaFileMapper;
import hcmute.nhom.kltn.model.MediaFile;
import hcmute.nhom.kltn.repository.MediaFileRepository;
import hcmute.nhom.kltn.service.MediaFileService;
import hcmute.nhom.kltn.service.firebase.FirebaseStorageService;
import hcmute.nhom.kltn.util.Constants;

/**
 * Class MediaFileServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class MediaFileServiceImpl
        extends AbstractServiceImpl<MediaFileRepository, MediaFileMapper, MediaFileDTO, MediaFile>
        implements MediaFileService {
    private static final Logger logger = LoggerFactory.getLogger(MediaFileServiceImpl.class);

    private static final String SERVICE_NAME = "MediaFileService";

    private final MediaFileRepository mediaFileRepository;

    private final FirebaseStorageService firebaseStorageService;

    @Override
    public MediaFileRepository getRepository() {
        return mediaFileRepository;
    }

    @Override
    public MediaFileMapper getMapper() {
        return MediaFileMapper.INSTANCE;
    }

    @Override
    public MediaFileDTO uploadFile(MultipartFile file) {
        String method = "UploadFile";
        logger.info(getMessageStart(SERVICE_NAME, method));
        logger.debug(getMessageInputParam(SERVICE_NAME, "file", file));
        try {
            File fileConverted = convertToFile(file);
            String fileName = file.getOriginalFilename();
            MediaFileDTO mediaFileDTO = new MediaFileDTO();
            mediaFileDTO.setFileName(fileName);
            mediaFileDTO.setFileType(this.getExtension(fileName));
            String fileType = file.getContentType();
            mediaFileDTO.setUrl(firebaseStorageService.uploadFile(fileConverted, fileType));
            mediaFileDTO.setRemovalFlag(false);
            mediaFileDTO = save(mediaFileDTO);
            fileConverted.delete();
            return mediaFileDTO;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(SERVICE_NAME, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public List<MediaFileDTO> uploadFiles(List<MultipartFile> files) {
        String method = "UploadFiles";
        logger.info(getMessageStart(SERVICE_NAME, method));
        logger.debug(getMessageInputParam(SERVICE_NAME, "files", files));
        try {
            List<MediaFileDTO> mediaFileDTOs = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                String fileType = file.getContentType();
                File fileConverted = convertToFile(file);
                MediaFileDTO mediaFileDTO = new MediaFileDTO();
                mediaFileDTO.setFileName(fileName);
                mediaFileDTO.setFileType(this.getExtension(fileName));
                mediaFileDTO.setUrl(firebaseStorageService.uploadFile(fileConverted, fileType));
                mediaFileDTO.setRemovalFlag(false);
                mediaFileDTOs.add(save(mediaFileDTO));
                fileConverted.delete();
            }
            logger.debug(getMessageOutputParam(SERVICE_NAME, "mediaFileDTOs", mediaFileDTOs));
            logger.info(getMessageEnd(SERVICE_NAME, method));
            return mediaFileDTOs;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(SERVICE_NAME, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public MediaFileDTO findByFileName(String fileName) {
        String method = "FindByFileName";
        logger.info(getMessageStart(SERVICE_NAME, method));
        logger.debug(getMessageInputParam(SERVICE_NAME, "fileName", fileName));
        try {
            MediaFile mediaFile = mediaFileRepository.findByFileName(fileName);
            if (mediaFile == null) {
                logger.debug(getMessageOutputParam(SERVICE_NAME, "mediaFile", mediaFile));
                logger.info(getMessageEnd(SERVICE_NAME, method));
                return null;
            }
            MediaFileDTO mediaFileDTO = getMapper().toDto(mediaFile, getCycleAvoidingMappingContext());
            logger.debug(getMessageOutputParam(SERVICE_NAME, "mediaFileDTO", mediaFileDTO));
            logger.info(getMessageEnd(SERVICE_NAME, method));
            return mediaFileDTO;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(SERVICE_NAME, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public MediaFileDTO getDefaultAvatar() {
        String method = "GetDefaultAvatar";
        logger.info(getMessageStart(SERVICE_NAME, method));
        MediaFileDTO mediaFileDTO = findByFileName("default-avatar.png");
        if (Objects.isNull(mediaFileDTO)) {
            String fileName = "default-avatar.png";
            mediaFileDTO.setFileName(fileName);
            mediaFileDTO.setFileType(fileName.substring(fileName.lastIndexOf(".")));
            mediaFileDTO.setUrl(Constants.DEFAULT_AVATAR);
            mediaFileDTO.setRemovalFlag(false);
            mediaFileDTO = save(mediaFileDTO);
        }
        logger.debug(getMessageOutputParam(SERVICE_NAME, "mediaFileDTO", mediaFileDTO));
        logger.info(getMessageEnd(SERVICE_NAME, method));
        return mediaFileDTO;
    }

    @Override
    public Boolean areEqualsTwoList(List<MediaFileDTO> list1, List<MediaFileDTO> list2) {
        if (Objects.isNull(list1) || Objects.isNull(list2) || list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            MediaFileDTO dto1 = list1.get(i);
            MediaFileDTO dto2 = list2.get(i);
            if (!areMediaFileDTOsEqual(dto1, dto2)) {
                return false;
            }
        }

        return true;
    }

    private static boolean areMediaFileDTOsEqual(MediaFileDTO dto1, MediaFileDTO dto2) {
        return dto1.getId().equals(dto2.getId()) &&
                dto1.getFileName().equals(dto2.getFileName()) &&
                dto1.getFileType().equals(dto2.getFileType()) &&
                dto1.getUrl().equals(dto2.getUrl());
    }

    private File convertToFile(MultipartFile multipartFile) {
        String method = "ConvertToFile";
        logger.info(getMessageStart(SERVICE_NAME, method));
        logger.debug(getMessageInputParam(SERVICE_NAME, "multipartFile", multipartFile));
        File tempFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(SERVICE_NAME, method));
            throw new SystemErrorException(e.getMessage());
        }
        logger.debug(getMessageOutputParam(SERVICE_NAME, "tempFile", tempFile));
        logger.info(getMessageEnd(SERVICE_NAME, method));
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}

package hcmute.nhom.kltn.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.MediaFileDTO;
import hcmute.nhom.kltn.mapper.MediaFileMapper;
import hcmute.nhom.kltn.model.MediaFile;
import hcmute.nhom.kltn.repository.MediaFileRepository;
import hcmute.nhom.kltn.service.MediaFileService;

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

    private final MediaFileRepository mediaFileRepository;

    @Override
    public MediaFileRepository getRepository() {
        return mediaFileRepository;
    }

    @Override
    public MediaFileMapper getMapper() {
        return MediaFileMapper.INSTANCE;
    }
}

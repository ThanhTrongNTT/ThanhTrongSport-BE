package hcmute.nhom.kltn.service.impl;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.SizeDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.SizeMapper;
import hcmute.nhom.kltn.model.Size;
import hcmute.nhom.kltn.repository.SizeRepository;
import hcmute.nhom.kltn.service.SizeService;

/**
 * Class SizeServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
public class SizeServiceImpl
        extends AbstractServiceImpl<SizeRepository, SizeMapper, SizeDTO, Size>
        implements SizeService {
    private static final Logger logger = LoggerFactory.getLogger(SizeServiceImpl.class);
    private static final String BL_NO = "SizeService";
    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public SizeRepository getRepository() {
        return this.sizeRepository;
    }

    @Override
    public SizeMapper getMapper() {
        return SizeMapper.INSTANCE;
    }

    @Override
    public SizeDTO updateSize(String id, SizeDTO sizeDTO) {
        String methodName = "updateSize";
        logger.info(getMessageStart(BL_NO, methodName));
        logger.info(getMessageInputParam(BL_NO, "id", id));
        logger.info(getMessageInputParam(BL_NO, "sizeDTO", sizeDTO));
        try {
            SizeDTO size = findById(id);
            if (Objects.isNull(size)) {
                throw new SystemErrorException("Size not found");
            }
            size.setName(sizeDTO.getName());
            size.setDescription(sizeDTO.getDescription());
            size.setValue(sizeDTO.getValue());
            size = save(size);
            logger.debug(getMessageOutputParam(BL_NO, "size", size));
            logger.info(getMessageEnd(BL_NO, methodName));
            return size;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new SystemErrorException("Update size failed");
        }
    }
}

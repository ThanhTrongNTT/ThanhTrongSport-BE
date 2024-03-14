package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.MediaFileDTO;
import hcmute.nhom.kltn.model.MediaFile;

/**
 * Class MediaFileMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface MediaFileMapper extends AbstractMapper<MediaFileDTO, MediaFile> {
    MediaFileMapper INSTANCE = Mappers.getMapper(MediaFileMapper.class);
}

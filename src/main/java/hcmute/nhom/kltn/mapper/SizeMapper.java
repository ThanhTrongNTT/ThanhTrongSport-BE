package hcmute.nhom.kltn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import hcmute.nhom.kltn.dto.SizeDTO;
import hcmute.nhom.kltn.model.Size;

/**
 * Class SizeMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Mapper()
public interface SizeMapper extends AbstractMapper<SizeDTO, Size>{
    SizeMapper INSTANCE = Mappers.getMapper(SizeMapper.class);
}

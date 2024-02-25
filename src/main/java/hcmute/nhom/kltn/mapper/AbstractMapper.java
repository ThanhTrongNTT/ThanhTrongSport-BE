package hcmute.nhom.kltn.mapper;

import org.mapstruct.Context;
import hcmute.nhom.kltn.dto.AbstractNonAuditDTO;
import hcmute.nhom.kltn.mapper.helper.CycleAvoidingMappingContext;
import hcmute.nhom.kltn.model.AbstractModel;

/**
 * Class AbstractMapper.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface AbstractMapper<D extends AbstractNonAuditDTO, E extends AbstractModel> {
    E toEntity(D dto);

    D toDto(E entity);
}

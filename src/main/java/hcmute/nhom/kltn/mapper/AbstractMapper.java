package hcmute.nhom.kltn.mapper;

import hcmute.nhom.kltn.dto.AbstractNonAuditDTO;
import hcmute.nhom.kltn.model.AbstractModel;

/**
 * Class AbstractMapper.
 *
 * @author: ThanhTrong
 **/
public interface AbstractMapper<D extends AbstractNonAuditDTO, E extends AbstractModel> {
    E toEntity(D dto);

    D toDto(E entity);
}

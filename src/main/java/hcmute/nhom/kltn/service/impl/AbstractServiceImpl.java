package hcmute.nhom.kltn.service.impl;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import hcmute.nhom.kltn.common.AbstractMessage;
import hcmute.nhom.kltn.dto.AbstractNonAuditDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.AbstractMapper;
import hcmute.nhom.kltn.mapper.helper.CycleAvoidingMappingContext;
import hcmute.nhom.kltn.model.AbstractModel;
import hcmute.nhom.kltn.service.AbstractService;

/**
 * Class AbstractServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Setter
@Getter
public class AbstractServiceImpl<R extends JpaRepository<E, String>, M extends AbstractMapper<D, E>,
        D extends AbstractNonAuditDTO, E extends AbstractModel>
        extends AbstractMessage
        implements AbstractService<D, E>, MessageSourceAware {

    protected R repository;
    protected D dto;
    protected E entity;
    protected M mapper;
    protected MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public CycleAvoidingMappingContext getCycleAvoidingMappingContext() {
        return new CycleAvoidingMappingContext();
    }

    protected D getDto() throws SystemErrorException {
        if (getMapper() == null) {
            throw new SystemErrorException("Can not load Mapper");
        }
        // Todo: Test before add CycleAvoidingMappingContext
        // return getMapper().toDto(entity, getCycleAvoidingMappingContext());
        return getMapper().toDto(entity);
    }

    protected E getEntity() throws SystemErrorException {
        if (getMapper() == null) {
            throw new SystemErrorException("Can not load Mapper");
        }
        // Todo: Test before add CycleAvoidingMappingContext
        // return getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        return getMapper().toEntity(dto);
    }

    public void setDTO(D dto) {
        this.dto = dto;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    @Override
    @Transactional
    public D save(D dto) {
        if (dto == null) {
            throw new SystemErrorException("Save not success. DTO is null");
        }

        // Todo: Test before add CycleAvoidingMappingContext
        // E item = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        E item = getMapper().toEntity(dto);
        entity = getRepository().save(item);
        return getMapper().toDto(entity);
    }

    @Override
    public E save(E entity) {
        return null;
    }

    @Override
    public List<D> save(List<D> dtos) {
        return null;
    }

    @Override
    public D findById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(D dto) {

    }

    @Override
    public List<D> findAll() {
        return null;
    }
}

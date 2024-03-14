package hcmute.nhom.kltn.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import hcmute.nhom.kltn.common.AbstractMessage;
import hcmute.nhom.kltn.dto.AbstractNonAuditDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.AbstractMapper;
import hcmute.nhom.kltn.mapper.helper.CycleAvoidingMappingContext;
import hcmute.nhom.kltn.model.AbstractModel;
import hcmute.nhom.kltn.service.AbstractService;
import hcmute.nhom.kltn.util.Utilities;

/**
 * Class AbstractServiceImpl.
 *
 * @author: ThanhTrong
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

    @Override
    @Transactional
    public D save(D dto) {
        if (dto == null) {
            throw new SystemErrorException("Save not success. DTO is null");
        }
        // E item = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        E item = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        entity = getRepository().save(item);
        return getMapper().toDto(entity, getCycleAvoidingMappingContext());
    }

    @Override
    public E save(E entity) {
        if (entity == null) {
            throw new SystemErrorException("Save not success. Entity is null");
        }
        return getRepository().save(entity);
    }

    @Override
    public List<D> save(List<D> dtos) {
        if (Objects.isNull(dtos) || dtos.isEmpty()) {
            throw new SystemErrorException("Save not success. DTOs is null");
        }
        List<E> entities = dtos.stream().map(item -> getMapper()
                .toEntity(item, getCycleAvoidingMappingContext())).collect(Collectors.toList());

        entities = getRepository().saveAll(entities);

        return entities.stream().map(item -> getMapper()
                .toDto(item, getCycleAvoidingMappingContext())).collect(Collectors.toList());
    }

    @Override
    public D findById(String id) {
        Optional<E> optional = getRepository().findById(id);
        if (optional.isEmpty()) {
            throw new SystemErrorException("Not found entity with id: " + id);
        }
        return getMapper().toDto(optional.get(), getCycleAvoidingMappingContext());
    }

    @Override
    public void delete(String id) {
        try {
            getRepository().deleteById(id);
        } catch (Exception e) {
            throw new SystemErrorException("Delete not success. Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(D dto) {
        try {
            getRepository().delete(getMapper().toEntity(dto, getCycleAvoidingMappingContext()));
        } catch (Exception e) {
            throw new SystemErrorException("Delete not success. Error: " + e.getMessage());
        }
    }

    @Override
    public List<D> findAll() {
        List<E> list = getRepository().findAll();
        return list.stream().map(item -> getMapper().toDto(item, getCycleAvoidingMappingContext())).collect(Collectors.toList());
    }

    @Override
    public Page<D> getPaging(int page, int size, String sortBy, String sortDir) {
        PageRequest pageRequest = Utilities.getPageRequest(page, size, sortBy, sortDir);
        Page<E> entities = getRepository().findAll(pageRequest);
        return entities.map(item -> getMapper().toDto(item, getCycleAvoidingMappingContext()));
    }
}

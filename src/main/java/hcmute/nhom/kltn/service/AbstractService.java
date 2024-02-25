package hcmute.nhom.kltn.service;

import java.util.List;
import hcmute.nhom.kltn.dto.AbstractNonAuditDTO;
import hcmute.nhom.kltn.model.AbstractModel;

/**
 * Class AbstractService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface AbstractService<D extends AbstractNonAuditDTO, E extends AbstractModel> {
    /**
     * Save.
     * @param dto : dto
     * @return D
     */
    D save(D dto);

    /**
     * Save.
     * @param entity : entity
     * @return E
     */
    E save(E entity);

    /**
     * Save.
     * @param dtos : dtos
     * @return List<D>
     */
    List<D> save(List<D> dtos);

    /**
     * Save.
     * @param id : id
     * @return D
     */
    D findById(String id);

    /**
     * Save.
     * @param id : id
     */
    void delete(String id);

    /**
     * Save.
     * @param dto : dto
     */
    void delete(D dto);

    /**
     * Save.
     * @return List<D>
     */
    List<D> findAll();
}

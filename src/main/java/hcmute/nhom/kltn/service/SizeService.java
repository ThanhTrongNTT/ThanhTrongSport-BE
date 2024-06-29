package hcmute.nhom.kltn.service;

import java.util.List;
import hcmute.nhom.kltn.dto.SizeDTO;
import hcmute.nhom.kltn.model.Size;

/**
 * Class SizeService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface SizeService extends AbstractService<SizeDTO, Size> {
    SizeDTO updateSize(String id, SizeDTO sizeDTO);
    List<SizeDTO> findAllByProductName(String productName);
}

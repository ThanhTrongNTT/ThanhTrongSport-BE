package hcmute.nhom.kltn.service.impl.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.SalesDTO;
import hcmute.nhom.kltn.mapper.product.SalesMapper;
import hcmute.nhom.kltn.model.product.Sales;
import hcmute.nhom.kltn.repository.product.SaleRepository;
import hcmute.nhom.kltn.service.product.SaleService;
import hcmute.nhom.kltn.service.impl.AbstractServiceImpl;

/**
 * Class SaleServiceImpl.
 *
 * @author: ThanhTrong
 **/
@Service
@RequiredArgsConstructor
public class SaleServiceImpl
        extends AbstractServiceImpl<SaleRepository, SalesMapper, SalesDTO, Sales>
        implements SaleService {
}

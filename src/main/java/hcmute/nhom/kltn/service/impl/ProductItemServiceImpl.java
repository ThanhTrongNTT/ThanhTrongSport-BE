package hcmute.nhom.kltn.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.ProductItemDTO;
import hcmute.nhom.kltn.mapper.ProductItemMapper;
import hcmute.nhom.kltn.model.product.ProductItem;
import hcmute.nhom.kltn.repository.ProductItemRepository;
import hcmute.nhom.kltn.service.ProductItemService;

/**
 * Class ProductItemServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class ProductItemServiceImpl
        extends AbstractServiceImpl<ProductItemRepository, ProductItemMapper, ProductItemDTO, ProductItem>
        implements ProductItemService {
    private final ProductItemRepository productItemRepository;

    private static final String BL_NO = "ProductItemService";

    @Override
    public ProductItemRepository getRepository() {
        return productItemRepository;
    }

    @Override
    public ProductItemMapper getMapper() {
        return ProductItemMapper.INSTANCE;
    }
}

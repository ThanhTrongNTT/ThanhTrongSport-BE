package hcmute.nhom.kltn.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.ProductMapper;
import hcmute.nhom.kltn.model.Product;
import hcmute.nhom.kltn.repository.ProductRepository;
import hcmute.nhom.kltn.service.ProductService;
import hcmute.nhom.kltn.util.Utilities;

/**
 * Class ProductServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends AbstractServiceImpl<ProductRepository, ProductMapper, ProductDTO, Product>
        implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final String BL_NO = "ProductService";
    private final ProductRepository productRepository;

    @Override
    public ProductRepository getRepository() {
        return productRepository;
    }

    @Override
    public ProductMapper getMapper() {
        return ProductMapper.INSTANCE;
    }

    @Override
    public Page<ProductDTO> searchProduct(String keyword, int pageNo, int pageSize, String sortBy, String sortDir) {
        String methodName = "searchProduct";
        logger.info(getMessageStart(BL_NO, methodName));
        logger.info(getMessageInputParam(BL_NO, "keyword", keyword));
        logger.info(getMessageInputParam(BL_NO, "pageNo", pageNo));
        logger.info(getMessageInputParam(BL_NO, "pageSize", pageSize));
        logger.info(getMessageInputParam(BL_NO, "sortBy", sortBy));
        logger.info(getMessageInputParam(BL_NO, "sortDir", sortDir));
        try {
            List<Product> products = productRepository.searchProduct(keyword);
            PageRequest pageRequest = Utilities.getPageRequest(pageNo, pageSize, sortBy, sortDir);
            List<ProductDTO> productDTOS = products.stream().map(product ->
                            getMapper().toDto(product, getCycleAvoidingMappingContext()))
                    .collect(Collectors.toList());
            Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOS, pageRequest, productDTOS.size());
            logger.debug(getMessageOutputParam(BL_NO, "productDTOPage", productDTOPage));
            logger.info(getMessageEnd(BL_NO, methodName));
            return productDTOPage;
        } catch (Exception e) {
            logger.error("Search product failed!", e);
            logger.info(getMessageEnd(BL_NO, methodName));
            throw new SystemErrorException("Error when forgot password");
        }
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        String methodName = "updateProduct";
        logger.info(getMessageStart(BL_NO, methodName));
        logger.info(getMessageInputParam(BL_NO, "id", id));
        logger.info(getMessageInputParam(BL_NO, "productDTO", productDTO));
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                logger.error("Product not found!");
                logger.info(getMessageEnd(BL_NO, methodName));
            }
            ProductDTO productDTOSave = getMapper().toDto(product, getCycleAvoidingMappingContext());
            productDTOSave.setProductName(productDTO.getProductName());
            productDTOSave.setPrice(productDTO.getPrice());
            productDTOSave.setDescription(productDTO.getDescription());
            productDTOSave.setImages(productDTO.getImages());
            productDTOSave.setProductCategory(productDTO.getProductCategory());
            productDTOSave.setQuantity(productDTO.getQuantity());
            productDTOSave.setRemovalFlag(productDTO.getRemovalFlag());
            productDTOSave.setCreatedDate(productDTO.getCreatedDate());
            productDTOSave.setCreatedBy(productDTO.getCreatedBy());
            productDTOSave.setModifiedBy(productDTO.getModifiedBy());
            productDTOSave.setModifiedDate(productDTO.getModifiedDate());
            save(productDTOSave);
            logger.info(getMessageEnd(BL_NO, methodName));
            return productDTO;
        } catch (Exception e) {
            logger.error("Update product failed!", e);
            logger.info(getMessageEnd(BL_NO, methodName));
            throw new SystemErrorException("Error when update product");
        }
    }
}

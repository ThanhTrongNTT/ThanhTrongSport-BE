package hcmute.nhom.kltn.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hcmute.nhom.kltn.dto.MediaFileDTO;
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.dto.SizeDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.MediaFileMapper;
import hcmute.nhom.kltn.mapper.ProductMapper;
import hcmute.nhom.kltn.model.MediaFile;
import hcmute.nhom.kltn.model.Product;
import hcmute.nhom.kltn.repository.ProductRepository;
import hcmute.nhom.kltn.service.MediaFileService;
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
    private final MediaFileService mediaFileService;

    @Override
    public ProductRepository getRepository() {
        return productRepository;
    }

    @Override
    public ProductMapper getMapper() {
        return ProductMapper.INSTANCE;
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO dto) {
        if (dto == null) {
            throw new SystemErrorException("Save not success. DTO is null");
        }
        // E item = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        Product item = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
        entity = getRepository().save(item);
        if (dto.getImages() != null) {
            List<MediaFile> mediaFiles = dto.getImages().stream().map(mediaFileDTO ->
                    MediaFileMapper.INSTANCE.toEntity(mediaFileDTO, getCycleAvoidingMappingContext()))
                    .collect(Collectors.toList());
            for (MediaFile mediaFile : mediaFiles) {
                mediaFile.setProduct(entity);
                mediaFileService.save(mediaFile);
            }
        }
        return getMapper().toDto(entity, getCycleAvoidingMappingContext());
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
    @Transactional
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        String methodName = "updateProduct";
        logger.info(getMessageStart(BL_NO, methodName));
        logger.info(getMessageInputParam(BL_NO, "id", id));
        logger.info(getMessageInputParam(BL_NO, "productDTO", productDTO));
        try {
            ProductDTO product = findById(id);
            if (!mediaFileService.areEqualsTwoList(product.getImages(), productDTO.getImages())) {
                for (MediaFileDTO mediaFileDTO : productDTO.getImages()) {
                    mediaFileService.delete(mediaFileDTO.getId());
                }

                product.setImages(productDTO.getImages());
            }
            product.setProductName(productDTO.getProductName());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setProductCategory(productDTO.getProductCategory());
            product.setSize(productDTO.getSize());
            product.setQuantity(productDTO.getQuantity());
            product.setCreatedDate(productDTO.getCreatedDate());
            product.setCreatedBy(productDTO.getCreatedBy());
            product.setModifiedBy(productDTO.getModifiedBy());
            product.setModifiedDate(productDTO.getModifiedDate());
            product = save(product);
            logger.debug(getMessageOutputParam(BL_NO, "product", product));
            logger.info(getMessageEnd(BL_NO, methodName));
            return product;
        } catch (Exception e) {
            logger.error("Update product failed!", e);
            logger.info(getMessageEnd(BL_NO, methodName));
            throw new SystemErrorException("Error when update product");
        }
    }

    @Override
    public void delete(String id) {
        try {
            ProductDTO productDTO = findById(id);
            for (MediaFileDTO mediaFileDTO : productDTO.getImages()) {
                mediaFileService.delete(mediaFileDTO.getId());
            }
            getRepository().deleteById(id);
        } catch (Exception e) {
            throw new SystemErrorException("Delete not success. Error: " + e.getMessage());
        }
    }

    @Override
    public Page<ProductDTO> searchProductByCategory(String categoryName, int pageNo, int pageSize, String sortBy, String sortDir) {
        String methodName = "searchProductByCategory";
        logger.info(getMessageStart(BL_NO, methodName));
        logger.info(getMessageInputParam(BL_NO, "categoryName", categoryName));
        logger.info(getMessageInputParam(BL_NO, "pageNo", pageNo));
        logger.info(getMessageInputParam(BL_NO, "pageSize", pageSize));
        logger.info(getMessageInputParam(BL_NO, "sortBy", sortBy));
        logger.info(getMessageInputParam(BL_NO, "sortDir", sortDir));
        try {
            List<ProductDTO> productDTOS;
            if (categoryName == "") {
                productDTOS = findAll();
            } else {
                List<Product> products = productRepository.searchProductByCategory(categoryName);
                productDTOS = products.stream().map(product ->
                                getMapper().toDto(product, getCycleAvoidingMappingContext()))
                        .collect(Collectors.toList());
            }

            PageRequest pageRequest = Utilities.getPageRequest(pageNo, pageSize, sortBy, sortDir);
            Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOS, pageRequest, productDTOS.size());
            logger.debug(getMessageOutputParam(BL_NO, "productDTOPage", productDTOPage));
            logger.info(getMessageEnd(BL_NO, methodName));
            return productDTOPage;
        } catch (Exception e) {
            logger.error("Search product by category failed!", e);
            logger.info(getMessageEnd(BL_NO, methodName));
            throw new SystemErrorException("Error when search product by category");
        }
    }

    @Override
    public Page<ProductDTO> searchProductByPrice(Long minPrice, Long maxPrice, int pageNo, int pageSize, String sortBy, String sortDir) {
        String method = "searchProductByPrice";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "minPrice", minPrice));
        logger.debug(getMessageInputParam(BL_NO, "maxPrice", maxPrice));
        logger.debug(getMessageInputParam(BL_NO, "pageNo", pageNo));
        logger.debug(getMessageInputParam(BL_NO, "pageSize", pageSize));
        logger.debug(getMessageInputParam(BL_NO, "sortBy", sortBy));
        logger.debug(getMessageInputParam(BL_NO, "sortDir", sortDir));
        try {
            List<Product> products = productRepository.searchProductByPrice(minPrice, maxPrice);
            PageRequest pageRequest = Utilities.getPageRequest(pageNo, pageSize, sortBy, sortDir);
            List<ProductDTO> productDTOS = products.stream().map(product ->
                            getMapper().toDto(product, getCycleAvoidingMappingContext()))
                    .collect(Collectors.toList());
            Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOS, pageRequest, productDTOS.size());
            logger.debug(getMessageOutputParam(BL_NO, "productDTOPage", productDTOPage));
            logger.info(getMessageEnd(BL_NO, method));
            return productDTOPage;
        } catch (Exception e) {
            logger.error("Search Product by price failed!", e);
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public Page<ProductDTO> getList(int pageNo, int pageSize, String sortBy, String sortDir) {
        String methodName = "getList";
        logger.info(getMessageStart(BL_NO, methodName));
        logger.info(getMessageInputParam(BL_NO, "pageNo", pageNo));
        logger.info(getMessageInputParam(BL_NO, "pageSize", pageSize));
        logger.info(getMessageInputParam(BL_NO, "sortBy", sortBy));
        logger.info(getMessageInputParam(BL_NO, "sortDir", sortDir));
        try {
            PageRequest pageRequest = Utilities.getPageRequest(pageNo, pageSize, sortBy, sortDir);
            List<Product> products = productRepository.findAll();
            List<ProductDTO> productDTOS = products.stream().map(product ->
                            getMapper().toDto(product, getCycleAvoidingMappingContext()))
                    .collect(Collectors.toList());
            // Nhóm các sản phẩm theo tên và tạo danh sách ProductDTO
            Map<String, List<ProductDTO>> groupedProducts = productDTOS.stream()
                    .collect(Collectors.groupingBy(ProductDTO::getProductName));

            List<ProductDTO> finalProductDTOs = new ArrayList<>();
            for (Map.Entry<String, List<ProductDTO>> entry : groupedProducts.entrySet()) {
                String productName = entry.getKey();
                List<ProductDTO> dtos = entry.getValue();

                Set<String> uniqueSizeIds = new HashSet<>();
                List<SizeDTO> sizes = new ArrayList<>();
                for (ProductDTO dto : dtos) {
                        if (uniqueSizeIds.add(dto.getSize().getId())) {
                            sizes.add(dto.getSize());
                        }
                }

                ProductDTO finalDto = dtos.get(0);
                finalDto.setSizes(sizes);
                finalProductDTOs.add(finalDto);
            }

            Page<ProductDTO> productDTOPage = new PageImpl<>(finalProductDTOs, pageRequest, finalProductDTOs.size());
            logger.debug(getMessageOutputParam(BL_NO, "productDTOPage", productDTOPage));
            logger.info(getMessageEnd(BL_NO, methodName));
            return productDTOPage;
        } catch (Exception e) {
            logger.error("Get list product failed!", e);
            logger.info(getMessageEnd(BL_NO, methodName));
            throw new SystemErrorException("Error when get list product");
        }
    }
}

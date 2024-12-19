package hcmute.nhom.kltn.service.impl.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.PaginationDTO;
import hcmute.nhom.kltn.dto.UserDTO;
import hcmute.nhom.kltn.dto.order.CouponDTO;
import hcmute.nhom.kltn.dto.order.OrderDTO;
import hcmute.nhom.kltn.dto.order.OrderItemDTO;
import hcmute.nhom.kltn.dto.product.ProductItemDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.order.OrderMapper;
import hcmute.nhom.kltn.model.order.Order;
import hcmute.nhom.kltn.model.product.Category;
import hcmute.nhom.kltn.repository.order.OrderRepository;
import hcmute.nhom.kltn.service.UserService;
import hcmute.nhom.kltn.service.impl.AbstractServiceImpl;
import hcmute.nhom.kltn.service.order.OrderItemService;
import hcmute.nhom.kltn.service.order.OrderService;
import hcmute.nhom.kltn.service.product.ProductItemService;
import hcmute.nhom.kltn.service.product.ProductService;
import hcmute.nhom.kltn.util.Utilities;

/**
 * Class OrderServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends AbstractServiceImpl<OrderRepository, OrderMapper, OrderDTO, Order>
        implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final String BL_NO = "OrderService";
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ProductItemService productItemService;
    private final OrderItemService orderItemService;

    @Override
    public OrderRepository getRepository() {
        return orderRepository;
    }

    @Override
    public OrderMapper getMapper() {
        return OrderMapper.INSTANCE;
    }

    @Override
    public PaginationDTO<OrderDTO> getOrderByUser(String email, int pageNo, int pageSize, String sortBy, String sortDir) {
        logger.info(getMessageStart(BL_NO, "getOrderByUser"));
        logger.debug(getMessageInputParam(BL_NO, "email", email));
        checkInputParamGetOrderByUser(email);
        Page<Order> orderPage;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        try {
            orderPage = getRepository().getOrderByUser(email, pageable);
            List<OrderDTO> orderDTOList = getMapper().toDtoList(orderPage.getContent(), getCycleAvoidingMappingContext());
            logger.debug(getMessageOutputParam(BL_NO, "orderPage", orderPage));
            logger.info(getMessageEnd(BL_NO, "getOrderByUser"));
            return PaginationDTO.<OrderDTO>builder()
                    .items(orderDTOList)
                    .currentPage(pageNo)
                    .pageSize(pageSize)
                    .totalItems(orderPage.getTotalElements())
                    .itemCount(orderPage.getNumberOfElements())
                    .totalPages(1)
                    .build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, "getOrderByUser"));
            throw new SystemErrorException(e.getMessage());
        }

    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO, List<OrderItemDTO> orderItemDTOList) {
        String method = "createOrder";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "orderDTO", orderDTO));
        try {
            if (!checkCoupon(orderDTO)) {
                throw new SystemErrorException("Coupon không hợp lệ!");
            }
            UserDTO userDTO = userService.findByEmail(orderDTO.getUser().getEmail());
            if (Objects.isNull(userDTO)) {
                throw new SystemErrorException("Người dùng không tồn tại!");
            }
            orderDTO.setUser(userDTO);
            orderDTO.setRemovalFlag(false);
            OrderDTO finalOrderDTO = save(orderDTO);
            orderItemDTOList.forEach(orderItemDTO -> {
                if(!checkStock(orderItemDTO, orderItemDTO.getProduct())) {
                    throw new SystemErrorException("Số lượng sản phẩm không đủ!");
                }
                orderItemDTO.setRemovalFlag(false);
                orderItemDTO.setOrder(finalOrderDTO);
                orderItemDTO.getProduct().setStock(orderItemDTO.getProduct().getStock() - orderItemDTO.getQuantity());
                productItemService.updateProductItem(orderItemDTO.getProduct().getId(), orderItemDTO.getProduct());
                orderItemService.save(orderItemDTO);
            });
            logger.debug(getMessageOutputParam(BL_NO, "orderDTO", orderDTO));
            logger.info(getMessageEnd(BL_NO, method));
            return finalOrderDTO;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    private boolean checkCoupon(OrderDTO orderDTO) {
        if (Objects.isNull(orderDTO.getCoupon())) {
            return true; // Không có coupon thì hợp lệ
        }

        CouponDTO coupon = orderDTO.getCoupon();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Định dạng ngày
        LocalDateTime endDate = LocalDateTime.parse(coupon.getEndDate(), formatter);
        LocalDateTime today = LocalDateTime.now();

        if (endDate.isBefore(today)) {
            throw new SystemErrorException("Coupon đã hết hạn sử dụng");
        }

        return true;
    }

    private Boolean checkStock(OrderItemDTO orderItemDTO, ProductItemDTO productItemDTO) {
        return orderItemDTO.getQuantity() <= productItemDTO.getStock();
    }

    @Override
    public PaginationDTO<OrderDTO> getAllOrderPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        String method = "getAllOrderPagination";
        logger.info(getMessageStart(BL_NO, method));
        logger.info(getMessageStart(BL_NO, method));
        logger.info(getMessageInputParam(BL_NO, "page", pageNo));
        logger.info(getMessageInputParam(BL_NO, "size", pageSize));
        logger.info(getMessageInputParam(BL_NO, "sortBy", sortBy));
        logger.info(getMessageInputParam(BL_NO, "sortDir", sortDir));
        Page<Order> orderPage;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        try {
            orderPage = orderRepository.getAllOrder(pageable);
            List<OrderDTO> orderDTOS =
                    getMapper().toDtoList(orderPage.getContent(), getCycleAvoidingMappingContext());
            logger.debug(getMessageOutputParam(BL_NO, "orderDTOS", orderDTOS));
            logger.info(getMessageEnd(BL_NO, method));
            return PaginationDTO.<OrderDTO>builder()
                    .items(orderDTOS)
                    .currentPage(pageNo)
                    .pageSize(pageSize)
                    .totalItems(orderPage.getTotalElements())
                    .itemCount(orderPage.getNumberOfElements())
                    .totalPages(orderPage.getTotalPages())
                    .build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException("Get all category pagination failed");
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        String method = "deleteOrder";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "orderId", orderId));
        try {
            OrderDTO orderDTO = findById(orderId);
            if (Objects.isNull(orderDTO)) {
                throw new SystemErrorException("Order không tồn tại!");
            }
            orderDTO.setRemovalFlag(true);
            getRepository().save(getMapper().toEntity(orderDTO, getCycleAvoidingMappingContext()));
            logger.info(getMessageEnd(BL_NO, method));
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public Integer countOrder() {
        String method = "countOrder";
        logger.info(getMessageStart(BL_NO, method));
        try {
            Long count = getRepository().countOrder();
            logger.debug(getMessageOutputParam(BL_NO, "count", count));
            logger.info(getMessageEnd(BL_NO, method));
            return Math.toIntExact(count);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public Double sumTotalPrice() {
        String method = "sumTotalPrice";
        logger.info(getMessageStart(BL_NO, method));
        try {
            Double sum = getRepository().sumTotalPrice();
            logger.debug(getMessageOutputParam(BL_NO, "sum", sum));
            logger.info(getMessageEnd(BL_NO, method));
            return sum;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    private void checkInputParamGetOrderByUser(String email) {
        if (Objects.isNull(email)) {
            String message = "Email is required!";
            logger.error(message);
            throw new SystemErrorException(message);
        }
    }
}

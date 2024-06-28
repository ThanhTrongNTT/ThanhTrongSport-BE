package hcmute.nhom.kltn.controller.v1;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hcmute.nhom.kltn.common.payload.ApiResponse;
import hcmute.nhom.kltn.dto.CartDTO;
import hcmute.nhom.kltn.dto.OrderDTO;
import hcmute.nhom.kltn.service.OrderService;
import hcmute.nhom.kltn.util.Constants;

/**
 * Class OrderController.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@RestController
public class OrderController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<Page<OrderDTO>>> getAllOrder(
            HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllOrder"));
        Page<OrderDTO> orderDTOPage = orderService.getPaging(pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllOrder"));
        return ResponseEntity.ok(new ApiResponse<>(true, orderDTOPage, "Get all order successfully!"));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<ApiResponse<OrderDTO>> searchOrder(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchOrder"));
        OrderDTO orderDTO = orderService.findById(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchOrder"));
        return ResponseEntity.ok(new ApiResponse<>(true, orderDTO, "Get order successfully!"));
    }

    @GetMapping("/orders/{email}")
    public ResponseEntity<ApiResponse<Page<OrderDTO>>> getOrderByUser(
            HttpServletRequest request,
            @PathVariable("email") String email,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getOrderByUser"));
        Page<OrderDTO> orderDTOPage = orderService.getOrderByUser(email, pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getOrderByUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, orderDTOPage, "Get order by user successfully!"));
    }

    @PostMapping("/order/add")
    public ResponseEntity<ApiResponse<OrderDTO>> addOrder(
            HttpServletRequest request,
            @RequestParam("email") String email,
            @RequestBody CartDTO cartDTO
            ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "createOrder"));
//        OrderDTO orderDTO = orderService.createOrder(email, cartDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "createOrder"));
        return ResponseEntity.ok(new ApiResponse<>(true, null, "Create order successfully!"));
    }
}

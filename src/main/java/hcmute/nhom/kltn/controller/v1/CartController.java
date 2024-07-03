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
import hcmute.nhom.kltn.dto.CartDetailDTO;
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.service.CartService;
import hcmute.nhom.kltn.util.Constants;

/**
 * Class CartController.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@RestController
public class CartController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final CartService cartService;

    public CartController(final CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/carts")
    public ResponseEntity<ApiResponse<Page<CartDTO>>> getAllCart(
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
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllCart"));
        Page<CartDTO> cartDTOPage = cartService.getPaging(pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllCart"));
        return ResponseEntity.ok(new ApiResponse<>(true, cartDTOPage, "Get all cart successfully!"));
    }

    @GetMapping("/carts/{email}")
    public ResponseEntity<ApiResponse<CartDTO>> getCartByUser(
            HttpServletRequest request,
            @PathVariable("email") String email
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getCartByUser"));
        CartDTO cartDTO = cartService.getCartByUser(email);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getCartByUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, cartDTO, "Get cart by user successfully!"));
    }

    @PostMapping("/cart/{email}/add")
    public ResponseEntity<ApiResponse<CartDTO>> addToCart(
            HttpServletRequest request,
            @PathVariable("email") String email,
            @RequestBody CartDetailDTO cartDetailDTO
            ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "addToCart"));
        CartDTO result = cartService.addToCart(email, cartDetailDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "addToCart"));
        return ResponseEntity.ok(new ApiResponse<>(true, result, "Add to cart successfully!"));
    }

    @PostMapping("/cart/{email}/update")
    public ResponseEntity<ApiResponse<CartDTO>> updateCart(
            HttpServletRequest request,
            @PathVariable("email") String email,
            @RequestBody CartDTO cartDTO

    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "addToCart"));
        cartDTO = cartService.updateCart(email, cartDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "addToCart"));
        return ResponseEntity.ok(new ApiResponse<>(true, cartDTO, "Add to cart successfully!"));
    }

    @PostMapping("/cart/add")
    public ResponseEntity<ApiResponse<CartDTO>> addToCartGuest(
            HttpServletRequest request,
            @RequestBody CartDetailDTO cartDetailDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "addToCartGuest"));
        CartDTO cartDTO = cartService.addToCartGuest(cartDetailDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "addToCartGuest"));
        return ResponseEntity.ok(new ApiResponse<>(true, cartDTO, "Add to cart successfully!"));
    }

    @PostMapping("/cart/update/{id}")
    public ResponseEntity<ApiResponse<CartDTO>> updateCartGuest(
            HttpServletRequest request,
            @PathVariable("id") String id,
            @RequestBody CartDTO cartDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "updateCartGuest"));
        cartDTO = cartService.updateCartGuest(id, cartDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "updateCartGuest"));
        return ResponseEntity.ok(new ApiResponse<>(true, cartDTO, "Update cart successfully!"));
    }
}

package hcmute.nhom.kltn.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hcmute.nhom.kltn.dto.CartDTO;
import hcmute.nhom.kltn.dto.CartDetailDTO;
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.dto.UserDTO;
import hcmute.nhom.kltn.exception.NotFoundException;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.CartDetailMapper;
import hcmute.nhom.kltn.mapper.CartMapper;
import hcmute.nhom.kltn.mapper.ProductMapper;
import hcmute.nhom.kltn.model.Cart;
import hcmute.nhom.kltn.model.CartDetail;
import hcmute.nhom.kltn.model.User;
import hcmute.nhom.kltn.repository.CartRepository;
import hcmute.nhom.kltn.service.CartDetailService;
import hcmute.nhom.kltn.service.CartService;
import hcmute.nhom.kltn.service.ProductService;
import hcmute.nhom.kltn.service.UserService;

/**
 * Class CartServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends AbstractServiceImpl<CartRepository, CartMapper, CartDTO, Cart>
        implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final String BL_NO = "CartService";
    private final CartRepository cartRepository;
    private final CartDetailService cartDetailService;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public CartRepository getRepository() {
        return cartRepository;
    }

    @Override
    public CartMapper getMapper() {
        return CartMapper.INSTANCE;
    }

    @Override
    public CartDTO getCartByUser(String email) {
        String method = "getCartByUser";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "email", email));
        checkInputParamGetCartByUser(email);
        try {
            Cart cart = cartRepository.getCartByUser(email);
            CartDTO cartDTO = getMapper().toDto(cart, getCycleAvoidingMappingContext());
            logger.debug(getMessageOutputParam(BL_NO, "cartDTO", cartDTO));
            logger.info(getMessageEnd(BL_NO, method));
            return cartDTO;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

//    @Override
//    @Transactional
//    public CartDTO save(CartDTO dto) {
//        if (dto == null) {
//            throw new SystemErrorException("Save not success. DTO is null");
//        }
//        // E item = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
//        Cart item = getMapper().toEntity(dto, getCycleAvoidingMappingContext());
//        entity = getRepository().save(item);
//
//        if (Objects.nonNull(dto.getCartDetails())) {
//            // Chuyển đổi CartDetailDTO thành CartDetail
//            List<CartDetail> cartDetails = dto.getCartDetails().stream().map(cartDetailDTO ->
//                            CartDetailMapper.INSTANCE.toEntity(cartDetailDTO, getCycleAvoidingMappingContext()))
//                    .collect(Collectors.toList());
//
//            // Lưu các chi tiết giỏ hàng vào cơ sở dữ liệu
//            for (CartDetail cartDetail : cartDetails) {
//                cartDetail.setCart(entity);
//                cartDetail.setRemovalFlag(false);
//                cartDetail = cartDetailService.save(cartDetail);
//                entity.getCartDetails().add(cartDetail);
//            }
//        }
//        return getMapper().toDto(entity, getCycleAvoidingMappingContext());
//    }

    @Override
//    @Transactional
    public CartDTO addToCart(String email, CartDetailDTO cartDetailDTO) {
        String method = "addToCart";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "email", email));
        logger.debug(getMessageInputParam(BL_NO, "cartDetailDTO", cartDetailDTO));
        checkInputParamAddToCart(email, cartDetailDTO);
        try {
            UserDTO userDTO = userService.findByEmail(email);
            if (Objects.isNull(userDTO)) {
                throw new NotFoundException("User not found!");
            }
            CartDTO cartDTO;
            CartDTO oldCart = getCartByUser(email);
            if (Objects.isNull(oldCart)) {
                // First Item add to cart
                cartDTO = new CartDTO();
                cartDTO.setUser(userDTO);
                cartDTO.setCartDetails(new ArrayList<>());
                cartDTO.setRemovalFlag(false);
                cartDTO = save(cartDTO);
            } else {
                cartDTO = oldCart;
            }
            ProductDTO product = productService.findById(cartDetailDTO.getProduct().getId());
            if (Objects.isNull(product)) {
                throw new NotFoundException("Product not found!");
            }
            Optional<CartDetailDTO> optionalCartDetail = cartDTO.getCartDetails().stream()
                    .filter(detail -> detail.getProduct().getId().equals(cartDetailDTO.getProduct().getId()))
                    .findFirst();
            if (optionalCartDetail.isPresent()) {
                CartDetailDTO cartDetail = optionalCartDetail.get();
                int newQuantity = cartDetail.getQuantity() + cartDetailDTO.getQuantity();
                if (newQuantity > product.getQuantity()) {
                    throw new RuntimeException("Quantity exceeds available stock");
                }
                cartDetail.setQuantity(newQuantity);
                cartDetailService.save(cartDetail);
            } else {
                if (cartDetailDTO.getQuantity() > product.getQuantity()) {
                    throw new RuntimeException("Quantity exceeds available stock");
                }
                CartDetailDTO cartDetail = new CartDetailDTO();
                cartDetail.setCart(cartDTO);
                cartDetail.setProduct(product);
                cartDetail.setQuantity(cartDetailDTO.getQuantity());
                cartDetail.setRemovalFlag(false);
                cartDetail = cartDetailService.save(cartDetail);
                cartDTO.getCartDetails().add(cartDetail);
            }
            // Add to cart
            cartDTO = save(cartDTO);
            logger.debug(getMessageOutputParam(BL_NO, "cartDTO", cartDTO));
            logger.info(getMessageEnd(BL_NO, method));
            return cartDTO;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public CartDTO updateCart(String email, CartDTO cartDTO) {
        String method = "updateCart";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "email", email));
        logger.debug(getMessageInputParam(BL_NO, "cartDTO", cartDTO));
        checkInputParamUpdateCart(email, cartDTO);
        try {
            CartDTO oldCart = getCartByUser(email);
            if (Objects.isNull(oldCart)) {
                String message = "Cart is not exist";
                logger.error(message);
                logger.info(getMessageEnd(BL_NO, method));
                throw new IllegalArgumentException(message);
            }
            oldCart.setCartDetails(cartDTO.getCartDetails());
            oldCart = save(oldCart);
            logger.debug(getMessageOutputParam(BL_NO, "cartDTO", oldCart));
            logger.info(getMessageEnd(BL_NO, method));
            return oldCart;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public CartDTO addToCartGuest(CartDetailDTO cartDetailDTO) {
        String method = "addToCartGuest";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "cartDetailDTO", cartDetailDTO));
        try {
            CartDTO cartDTO = new CartDTO();
            cartDetailDTO.setRemovalFlag(false);
            cartDetailDTO = cartDetailService.save(cartDetailDTO);
            cartDTO.setCartDetails(List.of(cartDetailDTO));
            cartDTO.setRemovalFlag(false);
            cartDTO = save(cartDTO);
            logger.debug(getMessageOutputParam(BL_NO, "cartDTO", cartDTO));
            logger.info(getMessageEnd(BL_NO, method));
            return cartDTO;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public CartDTO updateCartGuest(String id, CartDTO cartDTO) {
        String method = "updateCartGuest";
        logger.info(getMessageStart(BL_NO, method));
        logger.debug(getMessageInputParam(BL_NO, "id", id));
        logger.debug(getMessageInputParam(BL_NO, "cartDTO", cartDTO));
        try {
            CartDTO oldCart = findById(id);
            if (Objects.isNull(oldCart)) {
                String message = "Cart is not exist";
                logger.error(message);
                logger.info(getMessageEnd(BL_NO, method));
                throw new IllegalArgumentException(message);
            }
            oldCart.setCartDetails(cartDTO.getCartDetails());
            oldCart = save(oldCart);
            logger.debug(getMessageOutputParam(BL_NO, "cartDTO", oldCart));
            logger.info(getMessageEnd(BL_NO, method));
            return oldCart;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.info(getMessageEnd(BL_NO, method));
            throw new SystemErrorException(e.getMessage());
        }
    }

    @Override
    public Boolean deleteCartDetail(String email, String id) {
        // Tìm người dùng dựa trên email
        UserDTO user = userService.findByEmail(email);

        if (user == null) {
            throw new SystemErrorException("User not found!");
        }

        // Tìm giỏ hàng của người dùng
        Cart optionalCart = cartRepository.getCartByUser(user.getEmail());
        if (optionalCart == null) {
            throw new SystemErrorException("Cart not found for user!");
        }

        // Tìm chi tiết giỏ hàng cần xóa
        CartDetailDTO optionalCartDetail = cartDetailService.findById(id);
        if (optionalCartDetail == null) {
            throw new SystemErrorException("Cart detail not found!");
        }

        // Kiểm tra xem chi tiết giỏ hàng có thuộc giỏ hàng của người dùng không
//        if (!optionalCartDetail.getId().equals(cart.getId())) {
//            throw new SystemErrorException("Cart detail does not belong to user's cart!");
//        }

        // Đánh dấu chi tiết giỏ hàng là đã xóa
        optionalCartDetail.setRemovalFlag(true);
        cartDetailService.save(optionalCartDetail);

        // Xóa chi tiết giỏ hàng khỏi giỏ hàng
        optionalCart.getCartDetails().remove(optionalCartDetail);
        cartRepository.save(optionalCart);

        return true;
    }

    private void checkInputParamUpdateCart(String email, CartDTO cartDTO) {
        if (Objects.isNull(email)) {
            String message = "Email is null or empty";
            logger.error(message);
            logger.info(getMessageEnd(BL_NO, "updateCart"));
            throw new IllegalArgumentException(message);
        }
        if (Objects.isNull(cartDTO)) {
            String message = "CartDTO is null or empty";
            logger.error(message);
            logger.info(getMessageEnd(BL_NO, "updateCart"));
            throw new IllegalArgumentException(message);
        }
    }

    private void checkInputParamAddToCart(String email, CartDetailDTO cartDetailDTO) {
        if (Objects.isNull(email)) {
            String message = "Email is null or empty";
            logger.error(message);
            logger.info(getMessageEnd(BL_NO, "addToCart"));
            throw new IllegalArgumentException(message);
        }
        if (Objects.isNull(cartDetailDTO)) {
            String message = "CartDetailDTO is null or empty";
            logger.error(message);
            logger.info(getMessageEnd(BL_NO, "addToCart"));
            throw new IllegalArgumentException(message);
        }
    }

    private void checkInputParamGetCartByUser(String email) {
        if (Objects.isNull(email)) {
            String message = "Email is null or empty";
            logger.error(message);
            logger.info(getMessageEnd(BL_NO, "getCartByUser"));
            throw new IllegalArgumentException(message);
        }
    }
}

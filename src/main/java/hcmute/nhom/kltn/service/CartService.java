package hcmute.nhom.kltn.service;

import hcmute.nhom.kltn.dto.CartDTO;
import hcmute.nhom.kltn.dto.CartDetailDTO;
import hcmute.nhom.kltn.dto.ProductDTO;
import hcmute.nhom.kltn.model.Cart;

/**
 * Class CartService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public interface CartService extends AbstractService<CartDTO, Cart> {

    CartDTO getCartByUser(String email);

    CartDTO addToCart(String email, CartDetailDTO cartDetailDTO);

    CartDTO updateCart(String email, CartDTO cartDTO);

    CartDTO addToCartGuest(CartDetailDTO cartDetailDTO);

    CartDTO updateCartGuest(String id, CartDTO cartDTO);

    Boolean deleteCartDetail(String email, String id);
}

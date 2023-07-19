package by.eshop.service.impl;

import by.eshop.model.Cart;
import by.eshop.model.CartItem;
import by.eshop.repository.CartItemRepository;
import by.eshop.repository.CartRepository;
import by.eshop.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public void addProductToCart(int userId, int productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cart.getId());
            cartItem.setProductId(productId);
            cartItemRepository.save(cartItem);
        } else {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            cartRepository.save(newCart);
            CartItem cartItem = new CartItem();
            cartItem.setCartId(cartRepository.findByUserId(userId).getId());
            cartItem.setProductId(productId);
            cartItemRepository.save(cartItem);
        }

    }

    @Override
    public void deleteProductFromCart(int userId, int productId) {
        cartRepository.deleteProductFromCart(userId, productId);
    }

}
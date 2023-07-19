package by.eshop.service;

public interface CartService {

    void addProductToCart(int userId, int productId);

    void deleteProductFromCart(int userId, int productId);
}
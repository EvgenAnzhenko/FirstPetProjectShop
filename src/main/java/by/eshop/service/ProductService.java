package by.eshop.service;

import by.eshop.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllBy();

    List<Product> getProductsByCategory(String type);

    List<Product> findByCartItemsCartUserId(int userId);

    Product getProductById(int id);

    List<Product> getProductsBySearch(String category, String productName);
}

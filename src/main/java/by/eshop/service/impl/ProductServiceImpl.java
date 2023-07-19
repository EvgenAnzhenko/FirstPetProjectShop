package by.eshop.service.impl;

import by.eshop.model.Product;
import by.eshop.repository.ProductRepository;
import by.eshop.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllBy() {
        return productRepository.getAllBy();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }

    @Override
    public List<Product> findByCartItemsCartUserId(int userId) {
        return productRepository.findByCartItems_Cart_UserId(userId);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getProductsBySearch(String category, String productName) {
        return productRepository.getProductsBySearch(category, productName);
    }

}
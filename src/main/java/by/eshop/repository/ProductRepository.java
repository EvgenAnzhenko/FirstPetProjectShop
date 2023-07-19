package by.eshop.repository;

import by.eshop.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getAllBy();

    List<Product> getProductsByCategory(String category);

    Product getProductById(int id);

    List<Product> findByCartItems_Cart_UserId(int userId);

    @Query("SELECT p FROM Product p WHERE (:category = 'all-category' OR p.category = :category) AND LOWER(p.name) LIKE %:productName%")
    List<Product> getProductsBySearch(String category, String productName);
}
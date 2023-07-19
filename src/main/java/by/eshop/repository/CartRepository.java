package by.eshop.repository;

import by.eshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUserId(int id);

    @Modifying
    @Query("delete from CartItem ci where ci.cartId in (select c.id from Cart c where c.userId = :userId) and ci.productId = :productId")
    @Transactional
    void deleteProductFromCart(@Param("userId") int userId, @Param("productId") int productId);

}
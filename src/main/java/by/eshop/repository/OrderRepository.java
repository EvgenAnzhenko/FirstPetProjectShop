package by.eshop.repository;

import by.eshop.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    long countByUserId(int userId);

    Page<Order> getOrderByUserIdOrderByDateDesc(int userId, Pageable pageable);
}
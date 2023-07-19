package by.eshop.service;

import by.eshop.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    void makeOrder(int userId, int productId);

    Page<Order> getOrderByUserIdOrderByDateDesc(int userId, Pageable pageable);
}
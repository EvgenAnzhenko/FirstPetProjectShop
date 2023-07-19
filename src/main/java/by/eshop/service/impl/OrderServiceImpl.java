package by.eshop.service.impl;

import by.eshop.model.Order;
import by.eshop.model.Product;
import by.eshop.repository.OrderRepository;
import by.eshop.repository.ProductRepository;
import by.eshop.service.OrderService;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public void makeOrder(int userId, int productId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setDate(new Date());
        Optional<Product> product = productRepository.findById(productId);
        order.setTotalPrice(product.get().getPrice());
        order.setProductId(productId);
        orderRepository.save(order);
    }

    @Override
    public Page<Order> getOrderByUserIdOrderByDateDesc(int userId, Pageable pageable) {
        return orderRepository.getOrderByUserIdOrderByDateDesc(userId, pageable);

    }
}
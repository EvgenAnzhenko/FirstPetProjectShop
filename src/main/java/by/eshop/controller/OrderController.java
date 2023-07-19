package by.eshop.controller;

import by.eshop.model.Order;
import by.eshop.model.User;
import by.eshop.service.CartService;
import by.eshop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Setter
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;

    private final OrderService orderService;

    @PostMapping("/buy")
    public ModelAndView makeOrder(HttpSession session, @RequestParam("id") int productId) {
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        orderService.makeOrder(userId, productId);
        cartService.deleteProductFromCart(userId, productId);
        return new ModelAndView("redirect:/cart");
    }

    @GetMapping("/history")
    public ModelAndView getOrdersHistory(HttpSession session,
                                         Model model,
                                         @PageableDefault(size = 3) Pageable pageable) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            Page<Order> page = orderService.getOrderByUserIdOrderByDateDesc(userId, pageable);
            model.addAttribute("page", page);
            return new ModelAndView("ordershistory");
        }
        return new ModelAndView("signin");
    }
}
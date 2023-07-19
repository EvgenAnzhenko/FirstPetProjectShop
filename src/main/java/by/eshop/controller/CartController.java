package by.eshop.controller;

import by.eshop.model.Product;
import by.eshop.model.User;
import by.eshop.service.CartService;
import by.eshop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping
    public ModelAndView getCart(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            List<Product> products = productService.findByCartItemsCartUserId(userId);
            model.addAttribute("products", products);

            return new ModelAndView("cart");
        }
        return new ModelAndView("signin");
    }

    @GetMapping("/add-to-cart")
    public ModelAndView addProductToCart(HttpSession session,
                                         @RequestParam("id") int id,
                                         @RequestParam(value = "category", required = false) String category, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("category", category);

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            cartService.addProductToCart(user.getId(), id);
            if (category == null) {
                return new ModelAndView("redirect:/main");
            } else if (category.equals("one-product")) {
                return new ModelAndView("redirect:/product?id=" + id);
            } else {
                return new ModelAndView("redirect:/category?category=" + category);
            }
        } else {
            return new ModelAndView("signin");
        }
    }

    @PostMapping("/delete-from-cart")
    public ModelAndView deleteProductFromCart(HttpSession session, @RequestParam("id") int productId) {
        User user = (User) session.getAttribute("user");
        cartService.deleteProductFromCart(user.getId(), productId);
        return new ModelAndView("redirect:/cart");
    }
}
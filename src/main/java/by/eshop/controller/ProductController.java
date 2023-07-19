package by.eshop.controller;

import by.eshop.model.Product;
import by.eshop.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/main")
    public ModelAndView getMain(Model model) {
        List<Product> products = productService.getAllBy();
        model.addAttribute("products", products);
        return new ModelAndView("main");
    }

    @GetMapping("/category")
    public ModelAndView getProductCategoryPage(@RequestParam("category") String category, Model model) {
        List<Product> products = productService.getProductsByCategory(category);

        model.addAttribute("products", products);
        if (products != null && !products.isEmpty()) {
            String productCategoryRus = products.get(0).getCategoryRus();
            model.addAttribute("productCategoryRus", productCategoryRus);
        }
        return new ModelAndView("products");
    }

    @GetMapping("/product")
    public ModelAndView getProductPage(Model model, @RequestParam("id") int id) {
        Product product = productService.getProductById(id);
        model.addAttribute("oneProduct", product);
        return new ModelAndView("productpage");
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("category") String category, @RequestParam("product-name") String productName, Model model) {
        List<Product> products = productService.getProductsBySearch(category, productName);
        model.addAttribute("products", products);
        return new ModelAndView("resultsearch");
    }
}
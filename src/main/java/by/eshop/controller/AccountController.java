package by.eshop.controller;

import by.eshop.model.User;
import by.eshop.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    @GetMapping
    public ModelAndView getAccountPage(HttpSession session) {
        String path;
        if (session.getAttribute("user") != null) {
            path = "account";
        } else {
            path = "signin";
        }
        return new ModelAndView(path);
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView authorization(@Valid @ModelAttribute("user") User user,
                                      BindingResult bindingResult,
                                      HttpSession session,
                                      ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            populateError("email", modelAndView, bindingResult);
            populateError("password", modelAndView, bindingResult);
            modelAndView.setViewName("signin");
            return modelAndView;
        }

        if (userService.checkUser(user.getEmail(), user.getPassword())) {
            user = userService.getUserByLogin(user.getEmail(), user.getPassword());
            session.setAttribute("user", user);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("account");
        } else {
            modelAndView.setViewName("signin");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("authorization");
        session.removeAttribute("user");
        session.invalidate();
        return new ModelAndView("signin");
    }

    @PostMapping("/signup")
    public ModelAndView addUser(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration");
        }
        userService.addUser(user);
        return new ModelAndView("redirect:/main");
    }

    private void populateError(String field, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors(field)) {
            modelAndView.addObject(field + "Error", Objects.requireNonNull(bindingResult.getFieldError(field))
                                                           .getDefaultMessage());
        }
    }
}
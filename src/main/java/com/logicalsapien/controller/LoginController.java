package com.logicalsapien.controller;

import com.logicalsapien.entity.Galaxy;
import com.logicalsapien.entity.User;
import com.logicalsapien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/list-galaxies";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("content", "login");
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("content", "signup");
        return "index";
    }

    @PostMapping("/signup")
    public String createNewUser(@Valid User user, BindingResult bindingResult, ModelMap model) {

        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        userExists = userService.findUserByUserName(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }

        if (user.getPassword() == null || user.getPassword().length() < 4) {
            bindingResult
                    .rejectValue("password", "error.user",
                            "Password is required and should be minimum 4 characters long");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("content", "signup");
            return "index";
        }

        userService.saveUser(user);

        return "redirect:/index";
    }

}
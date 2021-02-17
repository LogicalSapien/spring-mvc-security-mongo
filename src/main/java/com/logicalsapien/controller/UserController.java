package com.logicalsapien.controller;

import com.logicalsapien.entity.Galaxy;
import com.logicalsapien.entity.Role;
import com.logicalsapien.entity.User;
import com.logicalsapien.repository.RoleRepository;
import com.logicalsapien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/list-users")
    public String showGalaxies(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("content", "list-users");
        return "index";
    }

//    @GetMapping("/add-galaxy")
//    public String showSignUpForm(Galaxy galaxy, ModelMap model) {
//        model.addAttribute("content", "add-galaxy");
//        return "index";
//    }
//
//    @PostMapping("/add-galaxy")
//    public String addGalaxy(@Valid Galaxy galaxy, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("content", "add-galaxy");
//            return "index";
//        }
//
//        galaxyService.saveGalaxy(galaxy);
//        return "redirect:/list-galaxies";
//    }
//
    @GetMapping("/update-user/{id}")
    public String showUpdateForm(@PathVariable("id") String username, ModelMap model) {
        User user = userService.findUserByUserName(username);

        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("rolesList", roles);
        model.addAttribute("content", "update-user");
        return "index";
    }

    @PostMapping("/update-user/{id}")
    public String updateGalaxy(@PathVariable("id") String username, @Valid User user,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            user.setUsername(username);
            model.addAttribute("content", "update-user");
            return "index";
        }

        userService.updateUser(username, user);
        return "redirect:/list-users";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteGalaxy(@PathVariable("id") String username, Model model) {
        userService.deleteGalaxy(username);
        return "redirect:/list-users";
    }
}
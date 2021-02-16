package com.logicalsapien.startup;

import com.logicalsapien.entity.Role;
import com.logicalsapien.entity.User;
import com.logicalsapien.repository.RoleRepository;
import com.logicalsapien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.*;

/**
 * Start Up component to set initial DB data
 */
@Controller
public class StartUpTasks {

    public static boolean dbUserListEmpty = false;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void populateDBWithRoles() {

        List<Role> roles = roleRepository.findAll();

        if (Objects.isNull(roles) || roles.size() == 0) {
            // add roles
            Role adminRole = new Role();
            adminRole.setRole("ADMIN");
            roleRepository.save(adminRole);

            Role writeRole = new Role();
            writeRole.setRole("WRITE");
            roleRepository.save(writeRole);

            Role readRole = new Role();
            readRole.setRole("READ");
            roleRepository.save(readRole);
        }
    }

    public void checkDBUserListEmpty() {
        List<User> users = userRepository.findAll();
        if (Objects.isNull(users) || users.size() == 0) {
            dbUserListEmpty = true;
        }
    }

    @RequestMapping(value = "/startup", method = RequestMethod.GET)
    public String signup(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        if (dbUserListEmpty) {
            model.addAttribute("content", "startup");
        } else {
            model.addAttribute("content", "login");
        }
        return "index";
    }

    @PostMapping("/startup")
    public String createNewUser(@Valid User user, BindingResult bindingResult) {

        List<User> users = userRepository.findAll();
        if (Objects.isNull(users) || users.size() == 0) {
            if (bindingResult.hasErrors()) {
                return "error-signup";
            }

            user.setEnabled(true);
            Role userRole = roleRepository.findByRole("ADMIN");
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);

            dbUserListEmpty = false;

            return "redirect:/index";
        }

        return "redirect:/index";
    }

}

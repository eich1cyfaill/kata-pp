package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/user")
    public String getUserInfo(Principal principal, Model model) {
        model.addAttribute("user", principal);
        return "user_info";
    }

    @GetMapping(value = "/")
    public String printWelcome(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "index";
    }

    @GetMapping("/create")
    public String createUserPage(Model model) {
        model.addAttribute("roles", roleService.getAllRolesList());
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public ModelAndView createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/update")
    public String updateUserPage(@RequestParam(value="id") Long id, Model model) {
        model.addAttribute("roles", roleService.getAllRolesList());
        model.addAttribute("user", userService.getOneUser(id));
        return "update-user";
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUser(user);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "admin";
    }
}

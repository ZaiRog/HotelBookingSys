package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUserAdmin")
    public String saveAdmin(@ModelAttribute("user") UserRegistrationDto user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/showFormForUpdateAdmin/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @GetMapping("/deleteUserAdmin/{id}")
    public String deleteUserAdmin(@PathVariable (value = "id") long id) {
        this.userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<User> list = userService.allUser();
        List<User> listUser = userService.findByEmail(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listUser", listUser);
        model.addAttribute("list", list);
        return "admin";
    }
}
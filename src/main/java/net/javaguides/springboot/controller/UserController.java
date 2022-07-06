package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.BookingDto;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
//@RequestMapping("/user_info/{id}")
public class UserController {


    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping("/user")
    public ModelAndView menuUser(){
        ModelAndView mav = new ModelAndView("user");
        User user = userService.getUserByEmail(getCurrentUsername());
        mav.addObject("user", user);
        return mav;
    }

//
//    @GetMapping("/user")
//    public String menuUser( Model model){
//        return "/user";
//    }

    @GetMapping("/user_info/{id}")
    public String menuUser_Info(@PathVariable( name = "id") int id,Model model){
        User user = userService.getUserById(id);
        model.addAttribute("userData", user);
        return "/user_info";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("user") UserRegistrationDto user) {
        userService.save(user);
        return "redirect:";
    }

    @ModelAttribute("booking")
    public BookingDto bookingRegistration(Model model, BookingDto bookingDto) {
        return new BookingDto();
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update_user_info";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUserAdmin(@PathVariable (value = "id") long id) {
        this.userService.deleteUserById(id);
        return "redirect:";
    }
}

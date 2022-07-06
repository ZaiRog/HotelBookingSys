package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.RoomType;
import net.javaguides.springboot.model.RoomView;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.BookingRepository;
import net.javaguides.springboot.repository.RoomTypeRepository;
import net.javaguides.springboot.repository.RoomViewRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.BookingService;
import net.javaguides.springboot.service.RoomTypeService;
import net.javaguides.springboot.service.RoomViewService;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.web.dto.RoomTypeDto;
import net.javaguides.springboot.web.dto.RoomViewDto;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/booking_room/{id}")
public class RoomController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;


    public RoomController(BookingService bookingService){
        super();
        this.bookingService = bookingService;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping
    public String bookingRoom(@PathVariable(name = "id") int id, Model model)
    {
        User user2 = userService.getUserById(id);
        model.addAttribute("user", user2);
        User user = userRepository.findByEmail(getCurrentUsername());
        List<Booking> booking = bookingRepository.findBookingByUserId(user.getId());
        model.addAttribute("listBooking", booking);
        return "/booking_room";
    }

}

package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.model.*;
import net.javaguides.springboot.repository.BookingRepository;
import net.javaguides.springboot.repository.CustomServiceRepository;
import net.javaguides.springboot.repository.RoomViewRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.BookingService;
import net.javaguides.springboot.web.dto.BookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private RoomViewRepository roomViewRepository;

    @Autowired
    private CustomServiceRepository customServiceRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        super();
        this.bookingRepository= bookingRepository;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Override
    public Booking saveBooking(BookingDto bookingDto) {
        RoomView roomView = roomViewRepository.findById(bookingDto.getIdRoom());
        CustomService customService = customServiceRepository.findById(bookingDto.getIdService());
        Set<CustomService> customServiceSet = new HashSet<CustomService>(Collections.singleton(customService));
        User user = userRepository.findByEmail(getCurrentUsername());

        roomView.setStatusBooking("Занят");

        Booking booking = new Booking(bookingDto.getDateBegin(),
                bookingDto.getDateEnd(),
                roomView.getPrice() * bookingDto.getColDay(),
                customService.getPrice() * bookingDto.getColDay(),
                bookingDto.getColDay(),
                bookingDto.getStatus(),
                roomView,
                user,
                customServiceSet);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> allBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking changeStatus(long id) {
        Booking booking = bookingRepository.findBookingById(id);
        booking.getRoomView().setStatusBooking("Свободный");
        booking.setStatus("Отмена");
        return bookingRepository.save(booking);
    }
}

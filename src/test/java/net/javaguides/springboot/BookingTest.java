package net.javaguides.springboot;

import net.javaguides.springboot.repository.BookingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class BookingTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void TestBooking() throws SQLException, ClassNotFoundException {
        Assertions.assertNotNull(bookingRepository.findBookingByUserId(1));
        Assertions.assertEquals(498, bookingRepository.
                findBookingByUserId(1).get(0).getPriceRoom());
    }

}

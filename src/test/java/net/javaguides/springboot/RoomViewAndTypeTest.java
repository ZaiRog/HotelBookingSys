package net.javaguides.springboot;

import net.javaguides.springboot.repository.RoomTypeRepository;
import net.javaguides.springboot.repository.RoomViewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomViewAndTypeTest {


    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Test
    void findByType() {
        Assertions.assertNotNull(roomTypeRepository.findByType("Standart"));
        Assertions.assertEquals("Lux", roomTypeRepository.findByType("Lux").getType());
    }

    @Autowired
    RoomViewRepository roomViewRepository;

    @Test
    void findById() {
        Assertions.assertNotNull(roomViewRepository.findById(36));
        Assertions.assertEquals(760, roomViewRepository.findById(36).getPrice());
    }
}

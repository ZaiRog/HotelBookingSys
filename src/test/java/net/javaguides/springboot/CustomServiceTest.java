package net.javaguides.springboot;

import net.javaguides.springboot.repository.CustomServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomServiceTest {

    @Autowired
    CustomServiceRepository customServiceRepository;

    @Test
    void findById() {
        Assertions.assertNotNull(customServiceRepository.findById(4L));
        Assertions.assertEquals("Wi-Fi", customServiceRepository.findById(4).getType());
    }

    @Test
    void findByType() {
        Assertions.assertNotNull(customServiceRepository.findByType("sauna"));
        Assertions.assertEquals("sauna", customServiceRepository.findByType("sauna").getType());
    }
}

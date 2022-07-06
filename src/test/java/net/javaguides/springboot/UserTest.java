package net.javaguides.springboot;

import net.javaguides.springboot.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        Assertions.assertNotNull(userRepository.findByEmail("admin@gmail.com"));
        Assertions.assertEquals("Egor", userRepository.findByEmail("egor@gmail.com").
                getFirstName());
    }
}

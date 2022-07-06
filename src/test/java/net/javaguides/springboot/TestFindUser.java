package net.javaguides.springboot;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.BooleanSupplier;

@SpringBootTest
public class TestFindUser {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
//        User user2 = new User();
//        user2.setEmail("egor@gmail.com");
//        userRepository.findByEmail(user2.getEmail());
        Assert.assertNotNull(userRepository.findByEmail("egor@gmail.com"));
    }
}

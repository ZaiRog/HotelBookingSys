package net.javaguides.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

import java.util.List;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	List<User> allUser();
	void saveUserById(User user);
	User getUserById(long id);
	void deleteUserById(long id);
	User getUserByEmail(String email);
	List<User> findByEmail(String email);
}

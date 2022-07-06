package net.javaguides.springboot.service.impl;
import java.util.*;
import java.util.stream.Collectors;

import net.javaguides.springboot.service.UserService;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Service
public class UserServiceImpl implements UserService {

//	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user=new User(registrationDto.getFirstName(),
							registrationDto.getLastName(), 
							registrationDto.getEmail(),
							passwordEncoder.encode(registrationDto.getPassword()), 
							Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		
		User user=userRepository.findByEmail(username); 
		
		if(user==null)
		{
			throw new UsernameNotFoundException("Invalid Username or password");			
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	
	}

	@Override
	public List<User> allUser(){
		return userRepository.findAll();
	}


	@Override
	public void saveUserById(User user) {
		this.userRepository.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public User getUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" User not found for id :: " + id);
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {

		User user = userRepository.findByEmail(email);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found for email: " + email);
		}
		return user;
	}

	@Override
	public List<User> findByEmail(String keyword) {
			List<User> userList = userRepository.findByEmailAndFirstNameAndLastName(keyword);
		return userList;
	}
}
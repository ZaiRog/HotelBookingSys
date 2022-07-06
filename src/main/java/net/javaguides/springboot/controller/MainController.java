package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}


	@GetMapping("/gallery")
	public String gallery() {
		return "/gallery";
	}

	@GetMapping("/about")
	public String about() {
		return "/about";
	}

	@GetMapping("/room")
	public String room() {
		return "/room";
	}




}

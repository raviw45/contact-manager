package com.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String home(Model m) {
		m.addAttribute("title","Contact-Home page");
		return "home";
	}
	
	
	@GetMapping("/about")
	public String about(Model m) {
		m.addAttribute("title","Contact-About Page");
		return "about";
	}
	
	@GetMapping("/login")
	public String signin(Model m) {
		m.addAttribute("title","Contact-LogIn Page");
		return "login";
	}
	
	@GetMapping("/signup")
	public String signUp(Model m) {
		m.addAttribute("title","Contact-SignUp Page");
		return "signUp";
	}
}

package com.contact.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact.model.User;
import com.contact.service.UserServices;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServices userServices;
     @ModelAttribute
	public void commonData(Model m,Principal principal) {
		String username=principal.getName();
		User user=this.userServices.getUserByUsername(username);
		m.addAttribute("user",user);
	}
	
	@GetMapping
	public String userHome(Model m,Principal principal) {
		m.addAttribute("title","User Home Page");
		return "normal/userhome";
	}
}

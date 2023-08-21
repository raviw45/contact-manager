package com.contact.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.contact.helper.Message;
import com.contact.model.User;
import com.contact.service.UserServices;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserServices userServices;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping
	public String home(Model m) {
		m.addAttribute("title", "Contact-Home page");
		return "home";
	}

	@GetMapping("/about")
	public String about(Model m) {
		m.addAttribute("title", "Contact-About Page");
		return "about";
	}

	@GetMapping("/signin")
	public String signin(Model m) {
		m.addAttribute("title", "Contact-LogIn Page");
		return "login";
	}

	@GetMapping("/signup")
	public String signUp(Model m) {
		m.addAttribute("title", "Contact-SignUp Page");
		m.addAttribute("user",new User());
		return "signup";
	}

	@PostMapping("/process_signup")
	public String processSignup(@Valid @ModelAttribute("user") User user,BindingResult result,
			@RequestParam("profileImage") MultipartFile getfile, HttpSession session, Model m) {
		try {
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			m.addAttribute("title","Processing Registration..");
			if (result.hasErrors()) {
				m.addAttribute("user", user);
				return "signup";
			}else
			{
				if (getfile.isEmpty()) {
					user.setuImageUrl("profile.png");
				} else {
					user.setuImageUrl(getfile.getOriginalFilename());
					File saveFile = new ClassPathResource("static/img").getFile();
					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + getfile.getOriginalFilename());
					Files.copy(getfile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					System.out.println("file uploaded!!!!!");
					System.gc();
				}
				System.out.println(user);
				this.userServices.save(user);
				session.setAttribute("message", new Message("Registered successfully!!!", "alert-success"));
				m.addAttribute("user",new User());
				return "redirect:/signup";
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something Went Wrong!!!" + e.getMessage(), "alert-danger"));
			m.addAttribute("user",user);
			return "redirect:/signup";
		}
	}
}

package com.contact.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.contact.helper.Message;
import com.contact.model.Contact;
import com.contact.model.User;
import com.contact.service.ContactServices;
import com.contact.service.UserServices;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServices userServices;
	@Autowired
	private ContactServices contactServices;

	@ModelAttribute
	public void commonData(Model m, Principal principal) {
		String username = principal.getName();
		User user = this.userServices.getUserByUsername(username);
		m.addAttribute("user", user);
	}

	@GetMapping
	public String userHome(Model m, Principal principal) {
		m.addAttribute("title", "User Home Page");
		return "normal/userhome";
	}

	@GetMapping("/profile")
	public String profile(Model m, Principal principal) {
		m.addAttribute("title", "User Profile Page");
		return "normal/profile";
	}

	@GetMapping("/add_contact")
	public String addcontact(Model m, Principal principal) {
		m.addAttribute("title", "Add-contact page");
		m.addAttribute("contact", new Contact());
		return "normal/addContact";
	}

	@PostMapping("/process_contact")
	public String handleContact(@Valid @ModelAttribute Contact contact, BindingResult result,
			@RequestParam("contactProfile") MultipartFile file, Model m, HttpSession session, Principal principal) {
		m.addAttribute("title", "Add-contact page");
		try {
			if (result.hasErrors()) {
				m.addAttribute("contact", contact);
				return "normal/addContact";
			} else {
				if (file.isEmpty()) {
					contact.setcImageUrl("profile.png");
				} else {
					contact.setcImageUrl(file.getOriginalFilename());
					File saveFile = new ClassPathResource("static/img").getFile();
					Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
					System.gc();
					System.out.println("image uploaded!!!");
				}
				m.addAttribute("contact", new Contact());
				String username = principal.getName();
				User user = this.userServices.getUserByUsername(username);
				user.getContacts().add(contact);
				contact.setUser(user);
				this.userServices.save(user);
				session.setAttribute("message", new Message("Contact added successfully!!!", "alert-success"));
				return "normal/addContact";
			}
		} catch (Exception e) {
			m.addAttribute("contact", contact);
			session.setAttribute("message", new Message("Something went wrong!!!!" + e.getMessage(), "alert-danger"));
			return "normal/addContact";
		}
	}

	@GetMapping("/show_contacts/{page}")
	public String showContacts(Model model, @PathVariable Integer page, Principal principal) {
		model.addAttribute("title", "View Contacts page");
		String username = principal.getName();
		User user = this.userServices.getUserByUsername(username);
		Pageable pageable = PageRequest.of(page, 3);
		Page<Contact> contacts = this.contactServices.getAllContacts(user.getUid(), pageable);
		model.addAttribute("contacts", contacts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", contacts.getTotalPages());
		return "normal/all_contacts";
	}

	@GetMapping("/{cid}/contact")
	public String contactProfile(@PathVariable("cid") Long cid, Model m, Principal principal) {
		m.addAttribute("title", "Contact-Profile page");
		String username = principal.getName();
		User user = this.userServices.getUserByUsername(username);
		Contact contact = this.contactServices.findById(cid);
		if (contact.getUser().getUid() == user.getUid()) {
			m.addAttribute("contact", contact);
		} else {
			m.addAttribute("contact", new Contact());
		}
		return "normal/contact_profile";
	}

	@GetMapping("/delete/{cid}")
	public String delete(@PathVariable("cid") Long cid, Principal principal) throws IOException {
		String username = principal.getName();
		User user = this.userServices.getUserByUsername(username);
		Contact contact = this.contactServices.findById(cid);
		String image = contact.getcImageUrl();
		if (!(image.equals("profile.png"))) {
			File deleteFile = new ClassPathResource("static/img").getFile();
			Path path = Paths.get(deleteFile.getAbsolutePath() + File.separator + image);
			System.out.println(contact.getcImageUrl());
			System.out.println("deleted");
			Files.deleteIfExists(path);
			System.gc();
		}

		if (contact.getUser().getUid() == user.getUid()) {
			user.getContacts().remove(contact);
			this.userServices.save(user);
		}
		return "redirect:/user/show_contacts/0";
	}

	@GetMapping("/update/{cid}")
	public String update(@PathVariable("cid") Long cid, Model m, Principal principal) {
		m.addAttribute("title", "Update-contact Page");
		Contact contact = this.contactServices.findById(cid);
		String username = principal.getName();
		User user = this.userServices.getUserByUsername(username);
		if (contact != null && contact.getUser().getUid() == user.getUid()) {
			m.addAttribute("contact", contact);
			return "normal/update_contact";
		}
		m.addAttribute("contact", new Contact());
		return "normal/update_contact";
	}

	@PostMapping("/process_update")
	public String updateHandle(@ModelAttribute Contact contact, @RequestParam("contactProfile") MultipartFile file,
			Model m, Principal principal, HttpSession session) {
		m.addAttribute("title", "Contact-Update page");
		try {
			Contact oldContactDetails = this.contactServices.findById(contact.getCid());
			if (!(file.isEmpty())) {
				contact.setcImageUrl(file.getOriginalFilename());
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				System.gc();
				System.out.println("image updated!!!");
			} else {
				contact.setcImageUrl(oldContactDetails.getcImageUrl());
			}
			System.out.println(contact.getCname());
			System.out.println(contact.getcImageUrl());
			System.out.println(contact.getCid());
			User user = this.userServices.getUserByUsername(principal.getName());
			contact.setUser(user);
			this.contactServices.save(contact);
			session.setAttribute("message", new Message("Contact Updated SuccessFully!!!!", "alert-success"));
			return "redirect:/user/" + contact.getCid() + "/contact";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong!!!!" + e.getMessage(), "alert-danger"));
			return "normal/update_contact";
		}
	}
}

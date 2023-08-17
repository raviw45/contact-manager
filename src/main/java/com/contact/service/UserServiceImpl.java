package com.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact.dao.UserRepository;
import com.contact.model.User;
@Service
public class UserServiceImpl implements UserServices {
    @Autowired
	private UserRepository userRepo;
    
    public User save(User user) {
    	return this.userRepo.save(user);
    }

	@Override
	public User getUserByUsername(String username) {
		return this.userRepo.getUserByUsername(username);
	}
}

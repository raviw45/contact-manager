package com.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.contact.dao.ContactRepository;
import com.contact.model.Contact;
@Service
public class ContactServiceImpl implements ContactServices {
    @Autowired
	private ContactRepository contactRepo;
    public Page<Contact> getAllContacts(Long userId,Pageable pageable){
    	return this.contactRepo.getAllContacts(userId,pageable);
    }
	@Override
	public Contact findById(Long cid) {
		
		return this.contactRepo.findById(cid).get();
	}
    @Override
    public void delete(Contact contact) {
    	this.contactRepo.delete(contact);
    }
	@Override
	public Contact save(Contact contact) {
		
		return this.contactRepo.save(contact);
	}
}

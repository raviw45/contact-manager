package com.contact.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.contact.model.Contact;

public interface ContactServices {
	public Page<Contact> getAllContacts(Long userId,Pageable pageable);
	public Contact findById(Long cid);
	public void delete(Contact contact);
	public Contact save(Contact contact);
}

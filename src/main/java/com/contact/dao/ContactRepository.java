package com.contact.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contact.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	@Query("from Contact as c where c.user.uid=:userId")
	public Page<Contact> getAllContacts(@Param("userId") Long userId,Pageable pageable);
}

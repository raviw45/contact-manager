package com.contact.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contact.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User as u WHERE u.uemail=:email")
	public User getUserByUsername(@Param("email") String email);
}

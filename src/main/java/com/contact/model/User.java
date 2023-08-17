package com.contact.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class User {
   
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long uid;
	
	@NotBlank(message="Name must be required!!!")
	private String uname;
	
	@Pattern(regexp ="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$",message="Invalid Email!!")
	@Column(nullable = false,unique = true)
	private String uemail;
	
	@Column(nullable=false,unique=true)
	@NotBlank(message="Password must required!!!")
	private String password;
	private String uImageUrl;
	private String about;
	private String role;
	private boolean isEnabled;
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "user",cascade = CascadeType.ALL)
	private List<Contact> contacts;
	
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getuImageUrl() {
		return uImageUrl;
	}
	public void setuImageUrl(String uImageUrl) {
		this.uImageUrl = uImageUrl;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", uemail=" + uemail + ", password=" + password
				+ ", uImageUrl=" + uImageUrl + ", about=" + about + ", role=" + role + ", isEnabled=" + isEnabled
				+ ", contacts=" + contacts + "]";
	}
	
}

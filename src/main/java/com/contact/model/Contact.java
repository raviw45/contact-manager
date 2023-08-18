package com.contact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cid;
    @NotBlank(message="Name must be required!!!!!")
	private String cname;
	private String nickName;
	@NotBlank(message="Work must be required!!!")
	private String work;
	@Pattern(regexp ="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$",message="Invalid Email!!")
	@Column(nullable = false,unique = true)
	private String cemail;
	private String cImageUrl;
	private String description;
	@NotBlank(message="Phone number must be required!!!!")
	@Size(min=10,max=10,message="Phone number must be of 10 digit number!!!")
	@Column(nullable=false,unique=true)
	private String phone;
	@ManyToOne
	private User user;
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getcImageUrl() {
		return cImageUrl;
	}
	public void setcImageUrl(String cImageUrl) {
		this.cImageUrl = cImageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", cname=" + cname + ", nickName=" + nickName + ", work=" + work + ", cemail="
				+ cemail + ", cImageUrl=" + cImageUrl + ", description=" + description + ", phone=" + phone + ", user="
				+ user + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.cid==((Contact)obj).getCid();
	}
	
}

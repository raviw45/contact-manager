package com.contact.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cid;
	private String cname;
	private String nickName;
	private String work;
	private String cemail;
	private String cImageUrl;
	private String description;
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
	
}

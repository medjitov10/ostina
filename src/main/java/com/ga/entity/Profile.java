package com.ga.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user_profile")
public class Profile {
	@Id
	@Column(name="id")
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id")
	private User user;

	@Column
	private String additionalEmail;
	
	@Column
	private String mobile;
	
	@Column
	private String address;
	
 
	public long getId() {
		return id;
	}

	public void setId(long profileId) {
		this.id = profileId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAdditionalEmail() {
		return additionalEmail;
	}

	public void setAdditionalEmail(String additionalEmail) {
		this.additionalEmail = additionalEmail;
	}

	public Profile(long profileId, User user, String additionalEmail, String mobile, String address) {
		super();
		this.id = profileId;
		this.user = user;
		this.additionalEmail = additionalEmail;
		this.mobile = mobile;
		this.address = address;
	}
	
	public Profile() {}
	

}

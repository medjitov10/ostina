package com.ga.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_profile")
public class Profile {
	@Id
	@Column(name="profile_id")
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private long profileId;
	
	@Column
	private String additionalEmail;
	
	@Column
	private String mobile;
	
	@Column
	private String address;
	
 
	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public String getEmail() {
		return additionalEmail;
	}

	public void setEmail(String email) {
		this.additionalEmail = email;
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
	
	

}

package com.cg.annapurna.OrderService.entity;

import org.springframework.data.annotation.Id;

public class Profile {

	@Id
	private int profileId;
	private String image;
	private String name;
	private int dateOfBirth;

	private Address address;
	private Cart cart;
	private String EmailId;
	private Long phoneNumber;
	private String gender;
	
	
	public Profile() {
		super();
	}
	
	public Profile(int profileId, String image, String name, int dateOfBirth, Address address, Cart cart,
			String emailId, Long phoneNumber, String gender) {
		super();
		this.profileId = profileId;
		this.image = image;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.cart = cart;
		EmailId = emailId;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(int dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", image=" + image + ", name=" + name + ", dateOfBirth="
				+ dateOfBirth + ", address=" + address + ", cart=" + cart + ", EmailId=" + EmailId + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + "]";
	}

	
	
	
}

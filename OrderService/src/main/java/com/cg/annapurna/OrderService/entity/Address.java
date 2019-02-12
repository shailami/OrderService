package com.cg.annapurna.OrderService.entity;

public class Address {

	private Integer houseNumber;	
	private String streetName;
	private String city;
	private Integer pinCode;
	private String state;
	private String country;
	
	
	
	public Address() {
		super();
	}



	public Address(int houseNumber, String streetName, String city, Integer pinCode, String state, String country) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.city = city;
		this.pinCode = pinCode;
		this.state = state;
		this.country = country;
	}



	public Integer getHouseNumber() {
		return houseNumber;
	}



	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}



	public String getStreetName() {
		return streetName;
	}



	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public Integer getPinCode() {
		return pinCode;
	}



	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	@Override
	public String toString() {
		return "Address [houseNumber=" + houseNumber + ", streetName=" + streetName + ", city=" + city + ", pinCode="
				+ pinCode + ", state=" + state + ", country=" + country + "]";
	}
	
}

	

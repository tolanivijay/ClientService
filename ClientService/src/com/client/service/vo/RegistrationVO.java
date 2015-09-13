package com.client.service.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Vijay Tolani
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="RegistrationDetails")
public class RegistrationVO {

	@XmlElement(name="FirstName")
	private String firstName;
	
	@XmlElement(name="SurName")
	private String surname;
	
	@XmlElement(name="EMail")
	private String email;
	
	@XmlElement(name="Password")
	private String password;
	
	@XmlElement(name="BirthDate")
	private String dateOfBirth;
	
	@XmlElement(name="MobileNumber")
	private String mobileNo;
	
	@XmlElement(name="PreferedEmailFormat")
	private String preferedEmailFormat;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPreferedEmailFormat() {
		return preferedEmailFormat;
	}

	public void setPreferedEmailFormat(String preferedEmailFormat) {
		this.preferedEmailFormat = preferedEmailFormat;
	}
}

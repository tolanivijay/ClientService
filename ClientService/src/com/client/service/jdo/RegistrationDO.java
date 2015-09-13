package com.client.service.jdo;

import java.util.Date;

/**
 * @author Vijay Tolani
 * DO used for persisting. It should always follow the structure of 
 * a Table.
 *
 */
public class RegistrationDO {
	
	private String firstName;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private Date dateOfBirth;
	
	private String mobileNo;
	
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

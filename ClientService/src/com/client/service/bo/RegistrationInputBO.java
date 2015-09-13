/**
 * 
 */
package com.client.service.bo;

import java.util.Date;

/**
 * @author Vijay Tolani
 * 
 * Input object for passing input params to the service
 *
 */
public class RegistrationInputBO {
	
	private String firstName;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private String confirmPassword;
	
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	@Override
	public String toString() {
		return "RegistrationInputBO [firstName=" + firstName + ", surname="
				+ surname + ", email=" + email + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", dateOfBirth="
				+ dateOfBirth + ", mobileNo=" + mobileNo
				+ ", preferedEmailFormat=" + preferedEmailFormat + "]";
	}
	

}

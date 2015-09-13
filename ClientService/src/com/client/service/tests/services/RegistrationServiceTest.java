package com.client.service.tests.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.client.service.bo.RegistrationInputBO;
import com.client.service.bo.ServiceResponseBO;
import com.client.service.constants.Constants;
import com.client.service.constants.ErrorConstants;
import com.client.service.dao.RegistrationDAO;
import com.client.service.dao.RegistrationFileDAOImpl;
import com.client.service.helper.Encryptor;
import com.client.service.helper.ReverseEncryptor;
import com.client.service.services.RegistrationService;

/**
 * @author Vijay Tolani
 *
 */
public class RegistrationServiceTest {
	
	private RegistrationService registrationService;
	
	/**
	 * Passes all validations and data is persisted on the disk
	 */
	@Test
	public void allValidSuccessMessage(){
		RegistrationInputBO inputBO =getTestBO();
		// Generating Random Email for the test to always succeed;
		inputBO.setEmail("RandomEmail@"+System.currentTimeMillis());
		ServiceResponseBO responseBO =registrationService.validateAndRegister(inputBO);
		System.out.println("Response for allValidSuccessMessage is "+responseBO);
		assertNotNull(responseBO);
		assertEquals(responseBO.getStatus(), Constants.SERVICE_SUCCESS);
		assertEquals(null, responseBO.getErrorText());
	}
	
	/**
	 * Passes all validations and data is persisted on the disk
	 */
	@Test
	public void persistSuccessWithoutPhoneNumber(){
		RegistrationInputBO inputBO =getTestBO();
		// Generating Random Email for the test to always succeed;
		inputBO.setEmail("RandomEmail@"+System.currentTimeMillis());
		inputBO.setMobileNo(null);
		ServiceResponseBO responseBO =registrationService.validateAndRegister(inputBO);
		System.out.println("Response for persistSuccessWithoutPhoneNumber is "+responseBO);
		assertNotNull(responseBO);
		assertEquals(responseBO.getStatus(), Constants.SERVICE_SUCCESS);
		assertEquals(null, responseBO.getErrorText());
	}
	
	/**
	 * Not passing a mandatory field which should cause validation
	 * error with appropriate message
	 */
	@Test
	public void missingFieldsFailureMessage(){
		RegistrationInputBO inputBO =getTestBO();
		// Generating Random Email for the test to always succeed;
		inputBO.setEmail("RandomEmail@"+System.currentTimeMillis());
		inputBO.setFirstName(null);
		ServiceResponseBO responseBO =registrationService.validateAndRegister(inputBO);
		System.out.println("Response for missingFieldsFailureMessage is "+responseBO);
		assertNotNull(responseBO);
		assertEquals(responseBO.getStatus(), Constants.SERVICE_FAILURE);
		assertNotNull(responseBO.getErrorText());
	}
	
	
	/**
	 * This test will pass only if dupe email already exists.
	 * Running the case more than once will ensure the same.
	 */
	@Test
	public void dupeEmailFailureMessage(){
		ServiceResponseBO responseBO =registrationService.validateAndRegister(getTestBO());
		System.out.println("Response for dupeEmailFailureMessage is "+responseBO);
		assertNotNull(responseBO);
		assertEquals(responseBO.getStatus(), Constants.SERVICE_FAILURE);
		assertEquals(responseBO.getErrorText(), ErrorConstants.DAO_DUPE_EMAIL);
	}
	
	/**
	 * Passing confirmPassword to be different than confirm Password
	 * for the validation error with appropriate message
	 */
	@Test
	public void passWordAndConfirmPassDifferentFailureMessage(){
		RegistrationInputBO inputBO =getTestBO();
		// Generating Random Email for to bypass dupe Email Validation;
		inputBO.setEmail("RandomEmail@"+System.currentTimeMillis());
		inputBO.setConfirmPassword("diffPassword");
		ServiceResponseBO responseBO =registrationService.validateAndRegister(inputBO);
		System.out.println("Response for passWordAndConfirmPassDifferentFailureMessage is "+responseBO);
		assertNotNull(responseBO);
		assertEquals(responseBO.getStatus(), Constants.SERVICE_FAILURE);
		assertEquals(responseBO.getErrorText(), ErrorConstants.SERVICE_PASSWORD_MISMATCH);
	}
	
	/**
	 * Passing age less than 18 for the validation error with appropriate message
	 * 
	 */
	@Test
	public void ageLessThan18FailureMessage(){
		RegistrationInputBO inputBO =getTestBO();
		// Generating Random Email for to bypass dupe Email Validation;
		inputBO.setEmail("RandomEmail@"+System.currentTimeMillis());
		inputBO.setDateOfBirth(getDate("01/01/1998"));
		ServiceResponseBO responseBO =registrationService.validateAndRegister(inputBO);
		System.out.println("Response for ageLessThan18FailureMessage is "+responseBO);
		assertNotNull(responseBO);
		assertEquals(responseBO.getStatus(), Constants.SERVICE_FAILURE);
		assertEquals(responseBO.getErrorText(), ErrorConstants.SERVICE_INVALID_AGE);
	}
	
	/**
	 * Passing invalid Email format for the validation error with appropriate message
	 */
	@Test
	public void invalidEmailFormatFailureMessage(){
		RegistrationInputBO inputBO =getTestBO();
		// Generating Random Email for to bypass dupe Email Validation;
		inputBO.setEmail("RandomEmail@"+System.currentTimeMillis());
		inputBO.setPreferedEmailFormat("InvalidFormat");
		ServiceResponseBO responseBO =registrationService.validateAndRegister(inputBO);
		System.out.println("Response for invalidEmailFormatFailureMessage is "+responseBO);
		assertNotNull(responseBO);
		assertEquals(responseBO.getStatus(), Constants.SERVICE_FAILURE);
		assertEquals(responseBO.getErrorText(), ErrorConstants.SERVICE_INVALID_EMAIL_FORMAT);
	}
	
	private RegistrationInputBO getTestBO(){
		RegistrationInputBO inputBO = new RegistrationInputBO();
		inputBO.setFirstName("TestName");
		inputBO.setSurname("TestSurname");
		inputBO.setMobileNo("+919999999999");
		inputBO.setPassword("testPass");
		inputBO.setConfirmPassword("testPass");
		inputBO.setEmail("abc@abc.com");
		inputBO.setDateOfBirth(getDate("01/01/1990"));
		inputBO.setPreferedEmailFormat(Constants.EMAIL_FORMAT_HTML);
		
		return inputBO;
	}
	
	private Date getDate(String strDate){
		Date date =null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    date = sdf.parse(strDate); 
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
	
	
	@Before
	public void setup(){
		RegistrationDAO registrationDao = new RegistrationFileDAOImpl();
		Encryptor encryptor = new ReverseEncryptor();
		registrationService = new RegistrationService();
		registrationService.setEncryptor(encryptor);
		registrationService.setRegistrationDao(registrationDao);
		
	}

}

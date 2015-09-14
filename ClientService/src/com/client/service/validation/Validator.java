package com.client.service.validation;

import java.util.Arrays;

import com.client.service.bo.RegistrationInputBO;
import com.client.service.constants.Constants;
import com.client.service.constants.ErrorConstants;
import com.client.service.util.Utils;

/**
 * Class used for all validation logic.
 * At the moment it is a standalone entity,but can be scaled for extension
 * if validation becomes more extensive or complex
 * 
 * @author Vijay Tolani
 * 
 */
public class Validator {

	/**
	 * Performs all Service validations and return error String if any issue,
	 * else null
	 * 
	 * @param inputBO
	 * @return
	 */
	public static String getErrorIfInvalid(RegistrationInputBO inputBO){
		String errorText = null;
		if (Utils.isAnyEntryEmpty(Arrays.asList(inputBO.getConfirmPassword(),inputBO.getEmail(),inputBO.getFirstName(),
											inputBO.getPassword(),inputBO.getPreferedEmailFormat(),inputBO.getSurname())) || inputBO.getDateOfBirth() == null){
			errorText= ErrorConstants.SERVICE_MANDATORY_FIELDS_NOT_PROVIDED;
			return errorText;
		}
		if (!inputBO.getPassword().equals(inputBO.getConfirmPassword())){
			errorText=ErrorConstants.SERVICE_PASSWORD_MISMATCH;
			return errorText;
		}
		if (Utils.getAgeInYears(inputBO.getDateOfBirth()) < 18){
			errorText=ErrorConstants.SERVICE_INVALID_AGE;
			return errorText;
		}
		if (!Constants.PERMITTED_EMAIL_FORMATS.contains(inputBO.getPreferedEmailFormat())){
			errorText=ErrorConstants.SERVICE_INVALID_EMAIL_FORMAT;
			return errorText;
		}
		
		return errorText;
	}
	
	
	
}

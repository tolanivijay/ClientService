package com.client.service.validation;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.client.service.bo.RegistrationInputBO;
import com.client.service.constants.Constants;
import com.client.service.constants.ErrorConstants;

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
		if (isCollectionEmpty(Arrays.asList(inputBO.getConfirmPassword(),inputBO.getEmail(),inputBO.getFirstName(),
											inputBO.getPassword(),inputBO.getPreferedEmailFormat(),inputBO.getSurname())) || inputBO.getDateOfBirth() == null){
			errorText= ErrorConstants.SERVICE_MANDATORY_FIELDS_NOT_PROVIDED;
			return errorText;
		}
		if (!inputBO.getPassword().equals(inputBO.getConfirmPassword())){
			errorText=ErrorConstants.SERVICE_PASSWORD_MISMATCH;
			return errorText;
		}
		if (getAgeInYears(inputBO.getDateOfBirth()) < 18){
			errorText=ErrorConstants.SERVICE_INVALID_AGE;
			return errorText;
		}
		if (!Constants.PERMITTED_EMAIL_FORMATS.contains(inputBO.getPreferedEmailFormat())){
			errorText=ErrorConstants.SERVICE_INVALID_EMAIL_FORMAT;
			return errorText;
		}
		
		return errorText;
	}
	
	
	/** 
	 * Have used raw flavour of Calender. However JODA libs are available
	 * for custom calculations around dates, which becomes an ideal chose if
	 * requirement becomes any more complex.
	 *   
	 * @param birthDate
	 * @return
	 */
	public static int getAgeInYears(Date birthDate){
		int diffInYears= 0;
		int diffInMonths =0;
		int diffInDays = 0;
		Calendar birthCalender = Calendar.getInstance();
		birthCalender.setTimeInMillis(birthDate.getTime());
		
		Calendar currentCalender = Calendar.getInstance();
		currentCalender.setTimeInMillis(System.currentTimeMillis());
		
		diffInYears = currentCalender.get(Calendar.YEAR) - birthCalender.get(Calendar.YEAR);
		diffInMonths = currentCalender.get(Calendar.MONTH) - birthCalender.get(Calendar.MONTH);
		diffInDays = currentCalender.get(Calendar.DATE) - birthCalender.get(Calendar.DATE);
		
		if (diffInMonths < 0){
			diffInYears--;
		}else if (diffInMonths == 0){
			if (diffInDays < 0){
				diffInYears--;
			}
		}
		
		return diffInYears;
	}
	
	/**
	 * @param strCollection
	 * @return
	 */
	private static boolean isCollectionEmpty(Collection<String> strCollection){
		boolean isEmpty = false;
		if (strCollection !=null){
			for (String str : strCollection){
				if (isStringEmpty(str)){
					isEmpty=true;
				}
			}
		}
		return isEmpty;
	}
	
	/**
	 * @param str
	 * @return
	 */
	private static boolean isStringEmpty(String str){
		boolean isEmpty = false;
		if (str == null || Constants.EMPTY_STRING.equals(str.trim())){
			isEmpty=true;
		}
		return isEmpty;
	}
}

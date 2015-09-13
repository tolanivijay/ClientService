package com.client.service.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vijay Tolani
 *
 */
public class ErrorConstants {
	
 
	public static final String DAO_GENERAL_FAILURE= "DAO_GENERAL_FAILURE";
	
	public static final String DAO_DUPE_EMAIL= "Email Already Present";
	
	public static List<String> SERVICE_MANDATORY_FILEDS = Arrays.asList("First Name","Surname","E-mail","Password","Confirm Password","Date of Birth","Preferred Mail Format");
	
	public static final String SERVICE_MANDATORY_FIELDS_NOT_PROVIDED= "Please provide all mandatory fields :" +SERVICE_MANDATORY_FILEDS.toString();
	
	public static final String SERVICE_INVALID_AGE= "Age < 18 not allowed";
	
	public static final String SERVICE_GENERAL_FAILURE= "Service General Failure.";
	
	public static final String SERVICE_PASSWORD_MISMATCH= "Password and Confirm Password Mismatch";
	
	public static final String SERVICE_INVALID_EMAIL_FORMAT= "Invalid Email Format, provide either of "+ Constants.PERMITTED_EMAIL_FORMATS.toString();

}

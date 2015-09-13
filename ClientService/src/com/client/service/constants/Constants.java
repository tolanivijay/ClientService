
package com.client.service.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vijay Tolani
 *
 */
public class Constants {
	
	public static final String SERVICE_SUCCESS = "Success";
	
	public static final String SERVICE_FAILURE = "Failure";
	
	public static final String EMAIL_FORMAT_HTML = "HTML";
	
	public static final String EMAIL_FORMAT_PLAIN_TEXT = "PLAIN_TEXT";
	
	public static final String EMAIL_FORMAT_RICH_TEXT = "RICH_TEXT";
	
	public static final String EMPTY_STRING = "";
	
	public static final List<String> PERMITTED_EMAIL_FORMATS = Arrays.asList(EMAIL_FORMAT_HTML,EMAIL_FORMAT_PLAIN_TEXT,EMAIL_FORMAT_RICH_TEXT);
	
	

}

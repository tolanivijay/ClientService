package com.client.service.helper;

/**
 * @author Vijay Tolani
 * 
 * Implementation of Encryptor which simply reverses the string
 *
 */
public class ReverseEncryptor implements Encryptor{

	@Override
	public String encryptString(String str) {
		String encrypted = null;
		if (str !=null){
			encrypted = new StringBuffer(str).reverse().toString();
		}
		return encrypted;
	}
	
	
}

package com.client.service.services;

import com.client.service.bo.DAOResponseBO;
import com.client.service.bo.RegistrationInputBO;
import com.client.service.bo.ServiceResponseBO;
import com.client.service.constants.Constants;
import com.client.service.constants.ErrorConstants;
import com.client.service.dao.RegistrationDAO;
import com.client.service.helper.Encryptor;
import com.client.service.util.Utils;
import com.client.service.validation.Validator;

/**
 * @author Vijay Tolani
 * 
 * Registration Service which takes Input BO which has various
 * registration params and persists the record. Its dependencies
 * should be enriched for the class to work.
 *
 */
public class RegistrationService {
	
	private RegistrationDAO registrationDao;
	
	private Encryptor encryptor;
	
	/**
	 * Core Method which does the initial validation followed by registration
	 * 
	 * @param inputBO
	 * @return
	 */
	public ServiceResponseBO validateAndRegister(RegistrationInputBO inputBO){
		ServiceResponseBO responseBO =new ServiceResponseBO();
		try{
			String errorText = Validator.getErrorIfInvalid(inputBO);
			if (errorText !=null){
				responseBO.setStatus(Constants.SERVICE_FAILURE);
				responseBO.setErrorText(errorText);
				return responseBO;
			}
			encrptPassWord(inputBO);
			DAOResponseBO daoResponseBO=registrationDao.persist(Utils.convertToDO(inputBO));
			
			if (daoResponseBO == null || daoResponseBO.getErrorText() !=null){
				responseBO.setStatus(Constants.SERVICE_FAILURE);
				if (daoResponseBO !=null){
					responseBO.setErrorText(daoResponseBO.getErrorText());
				}else{
					responseBO.setErrorText(ErrorConstants.SERVICE_GENERAL_FAILURE);
				}
			}else{
				responseBO.setStatus(Constants.SERVICE_SUCCESS);
			}
		}catch(Exception e){
			System.out.println("Exception while executing Registration Service for "+inputBO);
			responseBO.setStatus(Constants.SERVICE_FAILURE);
			responseBO.setErrorText(ErrorConstants.SERVICE_GENERAL_FAILURE);
		}
		
		return responseBO;
	}
	
	/**
	 * Simply replaces password with encrypted password
	 * 
	 * @param inputBO
	 */
	private void encrptPassWord(RegistrationInputBO inputBO){
		inputBO.setPassword(encryptor.encryptString(inputBO.getPassword()));
	}

	public void setRegistrationDao(RegistrationDAO registrationDao) {
		this.registrationDao = registrationDao;
	}

	public void setEncryptor(Encryptor encryptor) {
		this.encryptor = encryptor;
	}
	
	
	

}

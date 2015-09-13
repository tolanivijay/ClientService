package com.client.service.dao;

import com.client.service.bo.DAOResponseBO;
import com.client.service.jdo.RegistrationDO;

/**
 * @author Vijay Tolani
 *
 */
public interface RegistrationDAO {
	
	public DAOResponseBO persist(RegistrationDO clientDO);
	

}

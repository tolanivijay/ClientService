
package com.client.service.util;

import com.client.service.bo.RegistrationInputBO;
import com.client.service.jdo.RegistrationDO;
import com.client.service.vo.RegistrationVO;

/**
 * @author Vijay Tolani
 *
 */
public class Utils {
	
	public static RegistrationVO convertToVO(RegistrationDO detailsDO){
		RegistrationVO detailsVO = null;
		if (detailsDO == null){
			return detailsVO;
		}
		detailsVO = new RegistrationVO();
		
		detailsVO.setDateOfBirth(detailsDO.getDateOfBirth().toString());
		detailsVO.setEmail(detailsDO.getEmail());
		detailsVO.setFirstName(detailsDO.getFirstName());
		detailsVO.setSurname(detailsDO.getSurname());
		detailsVO.setMobileNo(detailsDO.getMobileNo());
		detailsVO.setPassword(detailsDO.getPassword());
		detailsVO.setPreferedEmailFormat(detailsDO.getPreferedEmailFormat());
		return detailsVO;
	}
	
	public static RegistrationDO convertToDO(RegistrationInputBO inputBO){
		RegistrationDO detailsDO = null;
		if (inputBO !=null){
			detailsDO = new RegistrationDO();
			detailsDO.setDateOfBirth(inputBO.getDateOfBirth());
			detailsDO.setEmail(inputBO.getEmail());
			detailsDO.setFirstName(inputBO.getFirstName());
			detailsDO.setMobileNo(inputBO.getMobileNo());
			detailsDO.setPassword(inputBO.getPassword());
			detailsDO.setPreferedEmailFormat(inputBO.getPreferedEmailFormat());
			detailsDO.setSurname(inputBO.getSurname());
		}
		return detailsDO;
	}

}

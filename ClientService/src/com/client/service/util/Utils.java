
package com.client.service.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.client.service.bo.RegistrationInputBO;
import com.client.service.constants.Constants;
import com.client.service.jdo.RegistrationDO;
import com.client.service.vo.RegistrationVO;

/**
 * Static Utils class used for stateless helper functions
 * 
 * @author Vijay Tolani
 *
 */
public final class Utils {
	
	private Utils(){}
	
	/**
	 * Converts to VO object given a DO object
	 * 
	 * @param detailsDO
	 * @return
	 */
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
	
	/**
	 * Converts to DO given a BO object
	 * 
	 * @param inputBO
	 * @return
	 */
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
	
	/** 
	 * Have used raw flavour of Calender. However JODA libs are available
	 * for custom calculations around dates, which becomes an ideal choice if
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
	 * Returns true if any string in collection is empty
	 * 
	 * @param strCollection
	 * @return
	 */
	public static boolean isAnyEntryEmpty(Collection<String> strCollection){
		boolean isEmpty = false;
		if (strCollection !=null){
			for (String str : strCollection){
				if (isStringEmpty(str)){
					isEmpty=true;
					break;
				}
			}
		}
		return isEmpty;
	}
	
	/**
	 * Returns true if the string passed is either null or empty 
	 * i.e. contains only spaces
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isStringEmpty(String str){
		boolean isEmpty = false;
		if (str == null || Constants.EMPTY_STRING.equals(str.trim())){
			isEmpty=true;
		}
		return isEmpty;
	}

}

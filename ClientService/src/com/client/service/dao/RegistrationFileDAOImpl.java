package com.client.service.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.client.service.bo.DAOResponseBO;
import com.client.service.constants.Constants;
import com.client.service.constants.ErrorConstants;
import com.client.service.jdo.RegistrationDO;
import com.client.service.util.Utils;
import com.client.service.vo.RegistrationVO;

/**
 * @author Vijay Tolani
 * Implementation of DAO which persists DO on the file system 
 * in xml format. Have hardcoded the dirLocation which should be
 * passed as dependency in real apps.
 *
 */
public class RegistrationFileDAOImpl implements RegistrationDAO{
	
	private String dirLocation="C:\\assignment\\";
	
	private String fileExtension = ".xml";
	
	

	/* 
	 * Performs the core business logic of persisting and all initial
	 * validation of Email Dupe check.
	 */
	@Override
	public DAOResponseBO persist(RegistrationDO registrationDO) {
		DAOResponseBO responseBO =new DAOResponseBO();
		if (registrationDO == null){
			responseBO.setErrorText(ErrorConstants.DAO_GENERAL_FAILURE);
			responseBO.setStatus(Constants.SERVICE_FAILURE);
			return responseBO;
		}
		if (isEmailAlreadyPresent(registrationDO)){
			responseBO.setErrorText(ErrorConstants.DAO_DUPE_EMAIL);
			responseBO.setStatus(Constants.SERVICE_FAILURE);
			return responseBO;
		}
		RegistrationVO detailsVO = Utils.convertToVO(registrationDO);
		if (!convertToXmlAndPersist(detailsVO)){
			responseBO.setErrorText(ErrorConstants.DAO_GENERAL_FAILURE);
			responseBO.setStatus(Constants.SERVICE_FAILURE);
			return responseBO;
		}
		responseBO.setStatus(Constants.SERVICE_SUCCESS);
		return responseBO;
	}
	
	/** Fetches all records from persistent store and compares if the
	 * email matches to the passed object
	 * 
	 * @param registrationDO
	 * @return
	 * 
	 */
	private boolean isEmailAlreadyPresent(RegistrationDO registrationDO){
		boolean isPresent = false;
		List<RegistrationVO> clientList =getAllPersistedDOs();
		if (clientList !=null){
			for (RegistrationVO details : clientList){
				if (details.getEmail().equals(registrationDO.getEmail())){
					isPresent=true;
					break;
				}
			}
		}
		return isPresent;
	}
	
	
	/**
	 * Fetches all records from persistent store
	 * @return
	 */
	private List<RegistrationVO> getAllPersistedDOs(){
		List<RegistrationVO> currentList = new ArrayList<RegistrationVO>();
		File currentFolder = new File(dirLocation);
		File[] allFiles =currentFolder.listFiles();
		if (allFiles !=null && allFiles.length > 0){
			for (int count =0 ; count <allFiles.length;count++){
				RegistrationVO details =convertToDO(allFiles[count]);
				if (details !=null){
					currentList.add(details);
				}
			}
		}
		System.out.println("Size of Persisted List is "+currentList.size());
		return currentList;
	}
	
	/**
	 * Parses given file from dir location and converts it to visual object
	 * 
	 * @param file
	 * @return
	 */
	private RegistrationVO convertToDO(File file){
		RegistrationVO details = null;
		if (file !=null){
			try{
				BufferedReader reader = new BufferedReader(new FileReader(file));
				StringBuilder builder =new StringBuilder();
				String line =null;
				while ((line = reader.readLine()) !=null){
					builder.append(line);
				}
				JAXBContext context = JAXBContext.newInstance(RegistrationVO.class);
				Unmarshaller unmarsheller = context.createUnmarshaller();
				details = (RegistrationVO)unmarsheller.unmarshal(new StringReader(builder.toString()));
			}catch(Exception e){
				System.out.println("Exception while reading from file :"+file.getName());
			}
			
		}
		
		return details;
	}
	
	
	/**
	 * Performs persist to File System after persist
	 * @param detailsVO
	 * @return
	 */
	private boolean convertToXmlAndPersist(RegistrationVO detailsVO){
		
		return persistToNewFile(convertToXml(detailsVO));
		
	}
	
	/**
	 * Converts to Xml
	 * 
	 * @param detailsVO
	 * @return
	 */
	private String convertToXml(RegistrationVO detailsVO){
		String xml=null;
		try{
			JAXBContext context = JAXBContext.newInstance(RegistrationVO.class);
			StringWriter writer = new StringWriter();
			Marshaller marshaller =context.createMarshaller();
			marshaller.marshal(detailsVO, writer);
			xml =writer.toString();
			System.out.println("xml to be written is "+xml);
		}
		catch (Exception e){
			System.out.println("Exception while marshalling to xml for " +detailsVO);
			e.printStackTrace();
		}
		return xml;
	}
	
	
	
	/**
	 * For the given xml creates a new file
	 * 
	 * @param xml
	 * @return
	 */
	private boolean persistToNewFile(String xml){
		boolean isPersisted =false;
		if (xml !=null){
			try{
				File file =new File(getCompleteFileUri());
				isPersisted =file.createNewFile();
				if (isPersisted){
					Writer writer = new BufferedWriter(new OutputStreamWriter(
					          new FileOutputStream(file)));
					    writer.write(xml);
					    writer.close();
				}
			}catch(Exception e){
				System.out.println("Exception while persisting file "+ e);
				isPersisted=false;
			}
			
		}
		return isPersisted;
	}
	
	/**
	 * Helper method to fetch complete file URI
	 * 
	 * @return
	 */
	private String getCompleteFileUri(){
		return dirLocation+System.currentTimeMillis() +fileExtension;
	}
	

}

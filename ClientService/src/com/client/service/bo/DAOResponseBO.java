/**
 * 
 */
package com.client.service.bo;

/**
 * @author Vijay Tolani
 * Response object which suggests the output of DAO operations
 *
 */
public class DAOResponseBO {
	
	private String status;
	
	private String errorText;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	@Override
	public String toString() {
		return "ClientDaoResponseBO [status=" + status + ", errorText="
				+ errorText + "]";
	}

}

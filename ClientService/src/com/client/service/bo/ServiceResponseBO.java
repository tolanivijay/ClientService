
package com.client.service.bo;

/**
 * @author Vijay Tolani
 * Encapsulate Services response
 *
 */
public class ServiceResponseBO {
	
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
		return "ServiceResponseBO [status=" + status + ", errorText="
				+ errorText + "]";
	}

}

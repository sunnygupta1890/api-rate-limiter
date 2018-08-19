package com.salesforce.apiratelimiter;

/**
 * Request will have the client and time of arrival information
 * 
 * @author sunngupt
 *
 */
public class Request {

	private String clientId;
	private long timeOfArrival;
	private RequestStatus requestStatus;

	public String getClientId() {
		return clientId;
	}

	public long getTimeOfArrival() {
		return timeOfArrival;
	}

	public boolean isExpired(long currentTime) {
		return currentTime - timeOfArrival > ApiConstants.REQ_EXPIRY_DURATION;
	}

	public void setStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}
}

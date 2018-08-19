package com.salesforce.apiratelimiter;

import java.util.concurrent.TimeUnit;

/**
 * LicenseType will have multiple types of licenses. (number of requests per time
 * unit)
 * 
 * It can support multiple time units.
 * 
 * @author sunngupt
 *
 */
public enum LicenseType {

	LOW(10, TimeUnit.SECONDS), MEDIUM(20, TimeUnit.SECONDS), HIGH(30, TimeUnit.SECONDS);

	private final int numOfRequests;
	private final TimeUnit timeUnit;

	private LicenseType(int numOfRequests, TimeUnit timeUnit) {
		this.numOfRequests = numOfRequests;
		this.timeUnit = timeUnit;
	}

	public int getNumOfRequests() {
		return numOfRequests;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

}

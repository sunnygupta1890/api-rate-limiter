package com.salesforce.apiratelimiter;

import java.util.concurrent.TimeUnit;

public interface ApiConstants {

	long REQ_EXPIRY_DURATION = 20;

	int globalLimit = 1000;
	
	TimeUnit globalLimitTimeUnit = TimeUnit.SECONDS;

}

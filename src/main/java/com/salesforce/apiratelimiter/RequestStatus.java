package com.salesforce.apiratelimiter;

public enum RequestStatus {

	IN_QUEUE, EXPIRED, THROTTLED, PROCESSED
}

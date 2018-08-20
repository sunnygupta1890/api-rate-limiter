package com.salesforce.apiratelimiter;

/**
 * Keeping track of request status
 */
public enum RequestStatus {

	IN_QUEUE, EXPIRED, THROTTLED, PROCESSED
}

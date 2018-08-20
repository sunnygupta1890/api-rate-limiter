package com.salesforce.apiratelimiter;

/**
 * RateLimiter - inform regarding if the rate limit for a client reached or not.
 * 
 */
public interface RateLimiter {

	public boolean isClientRateLimitReached(Request request);

	public boolean isGlobalRateLimitReached(Request request);

}

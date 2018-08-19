package com.salesforce.apiratelimiter;

public interface RateLimiter {

	public boolean isClientRateLimitReached(Request request);

	public boolean isGlobalRateLimitReached(Request request);

}

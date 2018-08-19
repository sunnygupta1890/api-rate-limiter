package com.salesforce.apiratelimiter;

import java.util.concurrent.BlockingQueue;

public class RequestProcessor {

	private BlockingQueue<Request> requestQueue;
	private RateLimiter rateLimiter;

	public RequestProcessor(BlockingQueue<Request> requestQueue, RateLimiter rateLimiter) {
		this.requestQueue = requestQueue;
		this.rateLimiter = rateLimiter;
	}

	public void processRequests() {
		while (requestQueue.isEmpty()) {
			try {
				Request request = requestQueue.take();
				long currentTime = System.currentTimeMillis();
				// check for expired request
				if (request.isExpired(currentTime)) {
					request.setStatus(RequestStatus.EXPIRED);
					continue;
				}

				// check for the global limit
				if (rateLimiter.isGlobalRateLimitReached(request)) {
					request.setStatus(RequestStatus.THROTTLED);
					continue;
				}

				// check for the client rate limit
				if (rateLimiter.isClientRateLimitReached(request)) {
					request.setStatus(RequestStatus.THROTTLED);
					continue;
				}

				// process the request if all went well.
				request.setStatus(RequestStatus.PROCESSED);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

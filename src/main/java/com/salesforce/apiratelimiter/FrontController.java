package com.salesforce.apiratelimiter;

import java.util.concurrent.BlockingQueue;

/**
 * FrontController class to add requests into queue.
 */
public class FrontController {

	private BlockingQueue<Request> requestQueue;

	public FrontController(BlockingQueue<Request> requestQueue) {
		this.requestQueue = requestQueue;
	}

	public void addRequestToQueue(Request request) {
		request.setStatus(RequestStatus.IN_QUEUE);
		requestQueue.add(request);
	}

}

package com.salesforce.apiratelimiter;

import java.util.concurrent.BlockingQueue;

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

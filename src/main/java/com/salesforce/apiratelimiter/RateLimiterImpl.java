package com.salesforce.apiratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class RateLimiterImpl implements RateLimiter {

	private Map<String, SortedMap<Long, Integer>> clientRequestCountMap = new HashMap<String, SortedMap<Long, Integer>>();

	private SortedMap<Long, Integer> globalRequestCountMap = new TreeMap<Long, Integer>();

	private ClientManager clientManager;

	public RateLimiterImpl(ClientManager clientManager) {
		this.clientManager = clientManager;
	}

	public boolean isClientRateLimitReached(Request request) {
		String clientId = request.getClientId();

		// Fetch request count for this client.
		SortedMap<Long, Integer> requestCountMap = clientRequestCountMap.get(clientId);
		// if nothing found for this client, Insert the timeStamp of the request with
		// count 1
		if (requestCountMap == null) {
			requestCountMap = new TreeMap<Long, Integer>();
			requestCountMap.put(request.getTimeOfArrival(), 1);
		} else {
			LicenseType licenseType = clientManager.getLicenseTypeForClient(clientId);
			TimeUnit timeUnit = licenseType.getTimeUnit();
			long timeOfArrival = request.getTimeOfArrival();
			long windowSize = timeOfArrival - timeUnit.toMillis(1);
			SortedMap<Long, Integer> tailMap = requestCountMap.tailMap(windowSize);

			// if the request in the current sliding window are more than the license limit.
			if (totalRequestCountInWindow(tailMap) >= licenseType.getNumOfRequests()) {
				return false;
			}
			requestCountMap = tailMap;
			if (tailMap.containsKey(timeOfArrival)) {
				int count = tailMap.get(timeOfArrival);
				tailMap.put(timeOfArrival, count + 1);
			} else {
				tailMap.put(timeOfArrival, 1);
			}

		}
		clientRequestCountMap.put(clientId, requestCountMap);
		return true;
	}

	public boolean isGlobalRateLimitReached(Request request) {
		long timeOfArrival = request.getTimeOfArrival();
		long windowSize = timeOfArrival - ApiConstants.globalLimitTimeUnit.toMillis(1);
		SortedMap<Long, Integer> tailMap = globalRequestCountMap.tailMap(windowSize);

		if (totalRequestCountInWindow(tailMap) >= ApiConstants.globalLimit) {
			return false;
		}

		if (tailMap.containsKey(timeOfArrival)) {
			int count = tailMap.get(timeOfArrival);
			tailMap.put(timeOfArrival, count + 1);
			globalRequestCountMap = tailMap;
		} else {
			globalRequestCountMap.put(timeOfArrival, 1);
		}

		return true;
	}

	private int totalRequestCountInWindow(SortedMap<Long, Integer> tailMap) {
		int totalCount = 0;
		for (Integer k : tailMap.values()) {
			totalCount += k;
		}
		return totalCount;
	}
}

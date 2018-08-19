package com.salesforce.apiratelimiter;

import java.util.HashMap;
import java.util.Map;

public class ClientManagerImpl implements ClientManager {

	private Map<String, LicenseType> namespaceLicenseMapping = new HashMap<String, LicenseType>();

	public void registerClient(final String clientId, final LicenseType licenseType) {
		namespaceLicenseMapping.put(clientId, licenseType);
	}

	public LicenseType getLicenseTypeForClient(final String clientId) {
		return namespaceLicenseMapping.get(clientId);
	}
}

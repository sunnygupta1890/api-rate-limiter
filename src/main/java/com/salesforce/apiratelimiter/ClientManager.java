package com.salesforce.apiratelimiter;

public interface ClientManager {

	public void registerClient(String clientId, LicenseType licenseType);

	public LicenseType getLicenseTypeForClient(final String clientId);
}

package com.salesforce.apiratelimiter;

/**
 * Responsible for registering the client with license.
 */
public interface ClientManager {

	public void registerClient(String clientId, LicenseType licenseType);

	public LicenseType getLicenseTypeForClient(final String clientId);
}

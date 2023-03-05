package com.tonnie.ipl.xpto.tracking.vehicle.util;

public final class Messages {
	
	private Messages() {
		throw new IllegalStateException("Utility class");
	}

	public static final String SENSOR_NOT_FOUND_FOR_ID = "Sensor not found. ID: %s";
	public static final String TELEMETRY_PROFILE_NOT_FOUND_FOR_ID = "Telemetry profile not found. ID: %s";
	public static final String SENSOR_ASSOCIATION_FAILED_SENSOR_NOT_FOUND = "Target Sensor not found. It's not possible to associate to a telemtry Profile. Sensor ID: %s";
	public static final String ENTITIES_NOT_FOUND_FOR_IDS = "Telemetry profile, Customer and Driver not found. IDs: %s, %s, %s";
}

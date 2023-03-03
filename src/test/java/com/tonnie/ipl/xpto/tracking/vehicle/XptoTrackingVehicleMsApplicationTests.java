package com.tonnie.ipl.xpto.tracking.vehicle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tonnie.ipl.xpto.tracking.vehicle.service.ISensorService;

@UnitTestProfile
class XptoTrackingVehicleMsApplicationTests {

	@Autowired
	private ISensorService service;
	
	@Test
	void contextLoads() {
		assertNotNull(service);
	}
}

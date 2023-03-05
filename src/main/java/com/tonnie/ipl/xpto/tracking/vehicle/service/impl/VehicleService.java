package com.tonnie.ipl.xpto.tracking.vehicle.service.impl;

import com.tonnie.ipl.xpto.tracking.vehicle.model.Vehicle;
import com.tonnie.ipl.xpto.tracking.vehicle.repository.VehicleRepository;
import com.tonnie.ipl.xpto.tracking.vehicle.service.IVehicleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Driver;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class VehicleService extends BaseEntityService<UUID, Vehicle, VehicleRepository> implements IVehicleService {

	private static String TelemetryProfileUri;
	private static String CustomerUri;
	private static String DriverUri;

	public VehicleService(VehicleRepository repository, @Value("${telemetry.profile.uri}") String TelemetryProfileUri, @Value("${customer.uri}") String CustomerUri, @Value("${driver.uri}") String DriverUri) {
		super(repository);
		this.TelemetryProfileUri = TelemetryProfileUri;
		this.CustomerUri = CustomerUri;
		this.DriverUri = DriverUri;
	}

	public static Set<Vehicle> convertSetIdsToListVehicles(Collection<String> listIds) {

		Set<Vehicle> vehicles = new HashSet<>();

		listIds.forEach(id->{
			Vehicle vehicle = new Vehicle();
			vehicle.setId(UUID.fromString(id));
			vehicles.add(vehicle);
		});

		return vehicles;
	}

	private static Boolean has_entity(String msUri, UUID entityId) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(msUri + entityId.toString()))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> Response = null;
		try {
			Response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return Response.statusCode() == 200;
	}

	public static Boolean is_valid(UUID TelemetryProfileId, UUID CustomerId, UUID DriverId) {
		//confirm existence of Driver, Customer and Telemetry Profile
		return has_entity(TelemetryProfileUri, TelemetryProfileId) && has_entity(CustomerUri, CustomerId) && has_entity(DriverUri, DriverId);
	}

}

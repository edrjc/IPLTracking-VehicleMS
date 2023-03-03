package com.tonnie.ipl.xpto.tracking.vehicle.controller;

import com.tonnie.ipl.xpto.tracking.vehicle.exception.EntityNotFoundException;
import com.tonnie.ipl.xpto.tracking.vehicle.mapper.MapperDtoEntity;
import com.tonnie.ipl.xpto.tracking.vehicle.model.Vehicle;
import com.tonnie.ipl.xpto.tracking.vehicle.openapi.api.VehiclesApi;
import com.tonnie.ipl.xpto.tracking.vehicle.openapi.model.*;
import com.tonnie.ipl.xpto.tracking.vehicle.service.IVehicleService;
import com.tonnie.ipl.xpto.tracking.vehicle.util.Messages;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.tonnie.ipl.xpto.tracking.vehicle.util.Constants.TRACE_ID;
import static com.tonnie.ipl.xpto.tracking.vehicle.util.Constants.X_TRACE_ID;

@RestController
@RequiredArgsConstructor
public class VehicleController implements VehiclesApi {

	private final IVehicleService service;
	private final MapperDtoEntity mapper;

	@Override
	public ResponseEntity<CreateVehicleResponseDto> createVehicle(CreateVehicleRequestDto createVehicleRequestDto) {

		HttpHeaders headers = new HttpHeaders();

		headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

		Vehicle newEntity = mapper.mapDtoToEntity(createVehicleRequestDto);

		newEntity = service.save(newEntity);

		return new ResponseEntity<>(mapper.mapEntityToCreateResponseDto(newEntity), headers, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Void> deleteVehicle(String vehicleId) {

		HttpHeaders headers = new HttpHeaders();

		headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

		Vehicle persistedEntity = service.findById(UUID.fromString(vehicleId)).orElseThrow(
				() -> new EntityNotFoundException(String.format(Messages.SENSOR_NOT_FOUND_FOR_ID, vehicleId)));

		service.delete(persistedEntity);

		return ResponseEntity.noContent().headers(headers).build();
	}

	@Override
	public ResponseEntity<GetVehicleResponseDto> getVehicle(String vehicleId) {

		HttpHeaders headers = new HttpHeaders();

		headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

		Vehicle persistedEntity = service.findById(UUID.fromString(vehicleId)).orElseThrow(
				() -> new EntityNotFoundException(String.format(Messages.SENSOR_NOT_FOUND_FOR_ID, vehicleId)));

		return ResponseEntity.ok().headers(headers).body(mapper.mapEntityToDto(persistedEntity));
	}

	@Override
	public ResponseEntity<ListVehiclesResponseDto> listVehicles() {

		HttpHeaders headers = new HttpHeaders();

		headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

		ListVehiclesResponseDto responseDto = mapper.convertVehicleCollectionToListDTO(service.findAll());

		return ResponseEntity.ok().headers(headers).body(responseDto);

	}

	@Override
	public ResponseEntity<Void> updateVehicle(String vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto) {

		HttpHeaders headers = new HttpHeaders();

		headers.set(X_TRACE_ID, MDC.get(TRACE_ID));

		Vehicle persistedEntity = service.findById(UUID.fromString(vehicleId)).orElseThrow(
				() -> new EntityNotFoundException(String.format(Messages.SENSOR_NOT_FOUND_FOR_ID, vehicleId)));

		Vehicle newEntity = mapper.mapDtoToEntity(updateVehicleRequestDto);

		persistedEntity.setCustomerId(newEntity.getCustomerId());
		persistedEntity.setTelemetryProfileId(newEntity.getTelemetryProfileId());
		persistedEntity.setDriverId(newEntity.getDriverId());
		persistedEntity.setPlateNumber(newEntity.getPlateNumber());
		persistedEntity.setVin(newEntity.getVin());
		persistedEntity.setColor(newEntity.getColor());

		service.update(persistedEntity);

		return ResponseEntity.noContent().headers(headers).build();
	}
}

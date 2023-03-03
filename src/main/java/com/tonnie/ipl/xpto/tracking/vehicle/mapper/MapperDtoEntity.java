package com.tonnie.ipl.xpto.tracking.vehicle.mapper;

import com.tonnie.ipl.xpto.tracking.vehicle.model.Vehicle;
import com.tonnie.ipl.xpto.tracking.vehicle.openapi.model.*;
import com.tonnie.ipl.xpto.tracking.vehicle.service.impl.VehicleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;

import static java.util.Objects.nonNull;

@Mapper(componentModel = "spring")
public interface MapperDtoEntity {

	// Named Converters
	@Named("ConvertUUIDToString")
	default String convertUUIDToString(UUID id) {
		return id.toString();
	}
	
	@Named("convertDTOArrayToCollection")
	default Set<Vehicle> convertDTOArrayToCollection(List<String> ids) {
		return nonNull(ids)?VehicleService.convertSetIdsToListVehicles(ids):new HashSet<>();
	}
	
	default ListVehiclesResponseDto convertVehicleCollectionToListDTO(Collection<Vehicle> entities) {
		ListVehiclesResponseDto responseDto = new ListVehiclesResponseDto();
		entities.forEach(vehicle -> responseDto.addContentItem(mapEntityToDto(vehicle)));
		responseDto.setTotalResults((long)entities.size());
		return responseDto;
	}
	
	
	// DTO to Entity
	@Mapping(target = "id", ignore = true)
	Vehicle mapDtoToEntity(CreateVehicleRequestDto dto);
	@Mapping(target = "id", ignore = true)
	Vehicle mapDtoToEntity(UpdateVehicleRequestDto dto);


	// Entity to DTO
	@Mapping(source = "id", target = "vehicleId", qualifiedByName = "ConvertUUIDToString")
	GetVehicleResponseDto mapEntityToDto(Vehicle entity);
	@Mapping(source = "id", target = "vehicleId", qualifiedByName = "ConvertUUIDToString")
	CreateVehicleResponseDto mapEntityToCreateResponseDto(Vehicle entity);

}
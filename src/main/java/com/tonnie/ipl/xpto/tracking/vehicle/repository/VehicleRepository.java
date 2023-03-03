package com.tonnie.ipl.xpto.tracking.vehicle.repository;

import com.tonnie.ipl.xpto.tracking.vehicle.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends IBaseRepository<Vehicle, UUID>{
  

}

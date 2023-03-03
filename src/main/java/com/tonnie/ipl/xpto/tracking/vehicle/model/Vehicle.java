package com.tonnie.ipl.xpto.tracking.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle implements IEntity<UUID> {

	@Id
	@Column(name = "vehicle_id")
	@GeneratedValue
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@Column(name = "customer_id")
	private UUID customerId;

	@Column(name = "telemetry_profile_id")
	private UUID telemetryProfileId;

	@Column(name = "driver_id")
	private UUID driverId;
	
	@Column(name = "plate_number")
	private String plateNumber;

	@Column(name = "vin")
	private String vin;

	@Column(name = "color")
	private String color;

}

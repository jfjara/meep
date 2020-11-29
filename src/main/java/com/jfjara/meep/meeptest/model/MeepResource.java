package com.jfjara.meep.meeptest.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeepResource implements Serializable {

	private static final long serialVersionUID = -2003254856920879422L;

	private String id;
	private String name;
	private Double x;
	private Double y;
	private String licencePlate;
	private Integer range;
	private Integer batteryLevel;
	private Integer helmets;
	private Integer seats;
	private String model;
	private String resourceImageId;
	private Boolean realTimeData;
	private String resourceType;
	private Integer companyZoneId;

	
	
	
}

package org.prj.arachne.domain.weather.valueObj;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Grid {
	
	private String village;
	private String city;
	private String country;
	private double latitude;
	private double longtitude;

}

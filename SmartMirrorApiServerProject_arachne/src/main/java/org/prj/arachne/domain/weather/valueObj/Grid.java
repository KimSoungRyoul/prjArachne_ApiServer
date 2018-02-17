package org.prj.arachne.domain.weather.valueObj;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grid {
	
	private String village;
	private String city;
	private String country;
	private double latitude;
	private double longtitude;

}

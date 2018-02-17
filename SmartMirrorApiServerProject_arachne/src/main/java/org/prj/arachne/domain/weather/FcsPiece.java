package org.prj.arachne.domain.weather;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FcsPiece {

	@Id
	@GeneratedValue
	private Long id;
	
	//몇시간뒤 예보인가?
	private int afterHour;
	
	private double windSpeed;
	private double windDirection;
	
	private String skyStatus;
	
	private double temperature;
	
	//습도
	private double humidity;
	
	private String rainPer;
	private String snowPer;
	
}

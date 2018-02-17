package org.prj.arachne.domain.weather;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.prj.arachne.domain.weather.valueObj.FcsText;
import org.prj.arachne.domain.weather.valueObj.FcstDaily;
import org.prj.arachne.domain.weather.valueObj.Grid;

import lombok.Data;

@Data
@Entity
public class WeatherForecast {

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseTime;
	
	//기상예보 위치
	@Embedded
	private Grid grid;
	
	//전국 예보
	private FcsText fcstext;
	
	// 지역예보
	private FcsText fcstextRegion;
	
	//3시간당 3일분량 일기예보 
	@ElementCollection
	private List<String[]> windList=new LinkedList<>();
	
	@ElementCollection
	private List<String> skyList=new LinkedList<>();
	
	@ElementCollection
	private List<String> temperatureList=new LinkedList<>();
	
	@ElementCollection
	private List<String> rainList=new LinkedList<>();
	
	//오늘 최저 최고 기온
	@Embedded
	private FcstDaily dayMinMax;
	
	
}

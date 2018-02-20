package org.prj.arachne.domain.weather;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	//오늘 최저 최고 기온
	@Embedded
	private FcstDaily dayMinMax;
	
	//전국 예보& 지역 예보
	@OneToMany
	@JoinColumn(name="wf_id")
	private List<FcsText> fcstextPair=new LinkedList<>();
	
	
	
	@OneToMany
	@JoinColumn(name="parent_fcs_id")
	private List<FcsPiece> fcsPieceList=new LinkedList<>();
	
	
	
	
	/*
	//3시간당 3일분량 일기예보 
	@ElementCollection
	private Map<String, Double> windList=new HashMap<>();
	
	//하늘 상태
	@ElementCollection
	private List<String> skyList=new LinkedList<>();
	
	//기온 상태
	@ElementCollection
	private List<String> temperatureList=new LinkedList<>();
	
	//강수량 또는 강수 여부
	@ElementCollection
	private List<String> rainList=new LinkedList<>();
	
	@ElementCollection
	private List<String> humidityList=new LinkedList<>();
	*/
	
	
	
	
	
	
}

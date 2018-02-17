package org.prj.arachne.domain.weather.valueObj;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class FcstDaily {

	private double todayMaxTem;
	private double tomorrowMaxTem;
	private double dayAfterTomorrowMaxTem;
	
	private double todayMinTem;
	private double tomorrowMinTem;
	private double dayAfterTomorrowMinTem;
	
	
	
}

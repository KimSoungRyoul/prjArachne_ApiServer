package org.prj.arachne.domain.weather.valueObj;

import java.util.Date;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FcstDaily {

	private double todayMaxTem;
	private double tomorrowMaxTem;
	private double dayAfterTomorrowMaxTem;
	
	private double todayMinTem;
	private double tomorrowMinTem;
	private double dayAfterTomorrowMinTem;
	
	
	
}

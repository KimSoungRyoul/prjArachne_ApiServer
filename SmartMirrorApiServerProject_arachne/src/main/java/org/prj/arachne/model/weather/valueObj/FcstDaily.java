package org.prj.arachne.model.weather.valueObj;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FcstDaily {

	private String todayMaxTem;
	private String tomorrowMaxTem;
	private String dayAfterTomorrowMaxTem;
	
	private String todayMinTem;
	private String tomorrowMinTem;
	private String dayAfterTomorrowMinTem;
	
	
	
}

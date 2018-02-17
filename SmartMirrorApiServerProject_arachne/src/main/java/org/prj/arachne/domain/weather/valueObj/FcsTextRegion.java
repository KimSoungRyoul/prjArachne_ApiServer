package org.prj.arachne.domain.weather.valueObj;

import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class FcsTextRegion {

	private Date timeReleaseR;
	private String locationNameR;
	private String textR;
	
}

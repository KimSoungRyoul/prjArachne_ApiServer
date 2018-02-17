package org.prj.arachne.domain.weather.valueObj;

import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class FcsText {
	
	private Date timeRelease;
	private String locationName;
	private String text;
	

}

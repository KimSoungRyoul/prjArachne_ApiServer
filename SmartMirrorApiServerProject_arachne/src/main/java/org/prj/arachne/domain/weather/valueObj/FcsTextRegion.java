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
public class FcsTextRegion {

	private Date timeReleaseR;
	private String locationNameR;
	private String textR;
	
}

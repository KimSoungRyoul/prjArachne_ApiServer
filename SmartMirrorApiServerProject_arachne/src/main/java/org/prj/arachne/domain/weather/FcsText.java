package org.prj.arachne.domain.weather;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FcsText {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date timeRelease;
	private String locationName;
	private String text;
	

}

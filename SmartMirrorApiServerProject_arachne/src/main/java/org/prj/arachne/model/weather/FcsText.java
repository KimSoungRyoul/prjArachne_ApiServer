package org.prj.arachne.model.weather;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

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
	
	@Lob
	private String text;
	

}

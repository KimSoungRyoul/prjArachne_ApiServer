package org.prj.arachne.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity {

	private String apiType;
	private ArachneStatus statusCode;
	private String message;
	
	
}

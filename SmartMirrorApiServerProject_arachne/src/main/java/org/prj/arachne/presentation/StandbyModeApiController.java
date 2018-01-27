package org.prj.arachne.presentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.prj.arachne.util.WeatherOpenApiUtil;
import org.prj.arachne.util.dto.PlaceCode;
import org.prj.arachne.util.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/standbymode")
public class StandbyModeApiController {

	@Autowired
	private WeatherOpenApiUtil wApi;
	
	
	@GetMapping("/weather/{placeName}")
	public ResponseEntity<Map<String, Object>> weather(@PathVariable("placeName") String place
														,@RequestParam("startDt") String startDt
														,@RequestParam("endDt") String endDt
														){
		
		ResponseEntity<Map<String, Object>> entity=null;
		
	 List<WeatherDTO> weatehrDTOs=	wApi.requestWeather(startDt, endDt, 108);
		
	 	Map<String, Object> values=new HashMap<>();
	 	
	 	StatusEntity status=new StatusEntity();
	 	status.setApiType("기상청관측자료(종관 일자료)OpenApi");
	 	status.setMessage("SUCCSS");
	 	status.setStatusCode(ArachneStatus.SUCCESS);
	 	
	 	values.put("status", status);
	 	values.put("weathers", weatehrDTOs);
	 	
	 	
	 	
	 	entity=new ResponseEntity<>(values,HttpStatus.OK);
	 
		
	
	 return entity;
		
	 
	}
	
}

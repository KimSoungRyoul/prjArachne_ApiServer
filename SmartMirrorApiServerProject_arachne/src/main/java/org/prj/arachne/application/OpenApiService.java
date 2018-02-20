package org.prj.arachne.application;

import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

	@Autowired
	private SKTWeatherOpenApiUtil sktwApi;
	
	
	public WeatherForecast requestwForecast(String city,String county,String village) {
		
		WeatherForecast wf=sktwApi.requestWeatherForecast(city, county, village);
		
		return wf;
	}
	
}

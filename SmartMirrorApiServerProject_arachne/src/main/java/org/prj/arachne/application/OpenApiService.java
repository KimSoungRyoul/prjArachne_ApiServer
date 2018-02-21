package org.prj.arachne.application;

import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.repository.WeatherForecastRepositroy;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OpenApiService {

	@Autowired
	private SKTWeatherOpenApiUtil sktwApi;
	
	@Autowired
	private WeatherForecastRepositroy wfRepo;
	
	public WeatherForecast requestwForecast(String city,String county,String village) {
		
		WeatherForecast wf=sktwApi.requestWeatherForecast(city, county, village);
		
		return wf;
	}
	
	
	@Transactional
	public void registerWeatherForcaset(WeatherForecast wf) {
		
		wfRepo.save(wf);
		
		
	}
	
}

package org.prj.arachne.application;

import javax.transaction.Transactional;

import org.prj.arachne.domain.weather.FcsPiece;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.repository.FcsTextRepository;
import org.prj.arachne.domain.weather.repository.FscPieceRepository;
import org.prj.arachne.domain.weather.repository.WeatherForecastRepositroy;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

	@Autowired
	private SKTWeatherOpenApiUtil sktwApi;
	
	@Autowired
	private WeatherForecastRepositroy wfRepo;
	
	@Autowired
	private FscPieceRepository fpRepo;
	
	@Autowired
	private FcsTextRepository ftRepo;
	
	
	@Transactional
	public WeatherForecast requestwForecast(String city,String county,String village) {
		
		WeatherForecast wf;
		
		wf=wfRepo.findTop1ByGridCityAndGridCountryAndGridVillageOrderByReleaseTime(city, county, village);		
		
		//WeatherForecast wf=sktwApi.requestWeatherForecast(city, county, village);
		
		return wf;
	}
	
	
	@Transactional
	public void registerWeatherForcaset(WeatherForecast wf) {
		
			fpRepo.save(wf.getFcsPieceList());
			ftRepo.save(wf.getFcstextPair());
			
			
			wfRepo.save(wf);
		
		
	}
	
}

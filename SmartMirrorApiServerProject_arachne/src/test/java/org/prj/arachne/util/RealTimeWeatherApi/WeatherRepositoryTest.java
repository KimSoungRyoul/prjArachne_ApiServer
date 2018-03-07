package org.prj.arachne.util.RealTimeWeatherApi;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.application.OpenApiService;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.repository.WeatherForecastRepositroy;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j
public class WeatherRepositoryTest {

	@Autowired
	private SKTWeatherOpenApiUtil weatherUtil;

	@Autowired
	private WeatherForecastRepositroy wfRepository;
	
	@Autowired
	private OpenApiService oaService;
	
	
	
	
	@Test
	public void BweatherRepositoryfindTest() {
		

		WeatherForecast wf=oaService.requestwForecast("서울", "중랑구", "상봉1동");
		
		
		
		assertNotNull(wf);
		
		
		log.info("-------------- weatherForecast Data----------------------------");
		log.info(wf.getGrid().toString());
		log.info(wf.getReleaseTime().toString());
		log.info(wf.getDayMinMax().toString());
		log.info(wf.getFcsPieceList().toString());
		log.info(wf.getFcstextPair().toString());
		
		
	}
	
	@Test
	//@Transactional
	public void AweatherRepositorySaveTest() {
		
		WeatherForecast wf1=weatherUtil.requestWeatherForecast("서울", "중랑구", "상봉1동");
		
		log.info(wf1.toString());
		
		oaService.registerWeatherForcaset(wf1);
		
		
		
		
		
	}
	
	
	
}

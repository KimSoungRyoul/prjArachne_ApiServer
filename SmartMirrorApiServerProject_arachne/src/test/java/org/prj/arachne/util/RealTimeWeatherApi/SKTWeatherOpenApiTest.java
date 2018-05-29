package org.prj.arachne.util.RealTimeWeatherApi;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.configuration.RestTemplate2Configuration;
import org.prj.arachne.domain.weather.SimpleWeather;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RestTemplate2Configuration.class,SKTWeatherOpenApiUtil.class})
@Log4j
public class SKTWeatherOpenApiTest {

	@Autowired
	private SKTWeatherOpenApiUtil weatherUtil;

	
	
	@Test
	public void restTemplateTest() {

			
		WeatherForecast wf= weatherUtil.requestWeatherForecast("서울", "중랑구","상봉1동");

		log.info("-------------- weatherForecast Data----------------------------");
		log.info(wf.getGrid().toString());
		log.info(wf.getReleaseTime());
		log.info(wf.getDayMinMax());
		log.info(wf.getFcsPieceList().toString());
		log.info(wf.getFcstextPair().toString());
		
		assertNotNull(wf);
		
	}

	@Test
	public void restTemplateTest2(){

		WeatherForecast wf= weatherUtil.requestWeatherForecast("37.342261","126.7280586");

		log.info("-------------- weatherForecast Data----------------------------");
		log.info(wf.getGrid().toString());
		log.info(wf.getReleaseTime());
		log.info(wf.getDayMinMax());
		log.info(wf.getFcsPieceList().toString());
		log.info(wf.getFcstextPair().toString());

		assertNotNull(wf);




	}
	
	@Test
	public void simpleWeatherTest(){

		SimpleWeather simpleWeather=weatherUtil.requestSimpleWeatherForecaset("37.342261","126.7280586");

		log.info(simpleWeather.toString());


	}
	
	
	

}

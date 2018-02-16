package org.prj.arachne.util;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.configuration.RestTemplateConfiguration;
import org.prj.arachne.util.dto.PlaceCode;
import org.prj.arachne.util.dto.WeatherDTO;
import org.prj.arachne.util.weather.pastWeather.WeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes= {WeatherOpenApiUtil.class,RestTemplateConfiguration.class})
public class WeatherRestTemplateTest {

	
	//@Autowired
	private WeatherOpenApiUtil wApiUtil;
	
	//@Test
	public void testRest() {
		
		List<WeatherDTO> dtos=wApiUtil.requestWeather("20180102", "20180103", 108);
		
		
		System.out.println(dtos.toString());
		
		
		
		
	}
	
	
}

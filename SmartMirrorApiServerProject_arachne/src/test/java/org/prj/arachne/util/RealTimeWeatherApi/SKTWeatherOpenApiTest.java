package org.prj.arachne.util.RealTimeWeatherApi;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.configuration.RestTemplate2Configuration;
import org.prj.arachne.domain.weather.FcsPiece;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.valueObj.Grid;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RestTemplate2Configuration.class,SKTWeatherOpenApiUtil.class })
@Log4j
public class SKTWeatherOpenApiTest {

	@Autowired
	private SKTWeatherOpenApiUtil weatherUtil;

	@Test
	public void restTemplateTest() throws UnsupportedEncodingException {

		String version = "1";
		// String latitude="36.1234";
		// String longtitude="127.1234";
		String city = "서울";
		String county = "중랑구";
		String village = "상봉1동";
		String foretxt = "Y";
		String appKey = "3a1a26a7-4b40-4b1d-a611-22b12de3c29f";

		
		WeatherForecast wf= weatherUtil.requestWeatherForecast("서울", "중랑구","상봉1동");

		log.info("-------------- weatherForecast Data----------------------------");
		log.info(wf.toString());
		
		
	}
	
	
	

}

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
import org.prj.arachne.domain.weather.valueObj.Grid;
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
@SpringBootTest(classes = { RestTemplate2Configuration.class })
@Log4j
public class SKTWeatherOpenApiTest {

	@Autowired
	@Qualifier("weather")
	private RestTemplate restTemplate;

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

		try {
			URI uri = UriComponentsBuilder.newInstance().scheme("https").host("api2.sktelecom.com")
					.path("/weather/forecast/3days").queryParam("version", version).queryParam("city", city)
					.queryParam("county", county).queryParam("village", village).queryParam("foretxt", foretxt).build()
					.encode("UTF-8").toUri();

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("charset", "UTF-8");
			headers.add("content-type", "application/json");
			headers.add("appKey", appKey);

			HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

			System.out.println(uri);

			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
					String.class);

			System.out.println("------------------------");
			System.out.println();

			JSONObject rootJsonObj = new JSONObject(responseEntity.getBody().toString());

			System.out.println(rootJsonObj);

			System.out.println(rootJsonObj.getJSONObject("weather").getJSONArray("forecast3days"));

			JSONArray jArr = rootJsonObj.getJSONObject("weather").getJSONArray("forecast3days");

			rootJsonObj = jArr.getJSONObject(0);

			JSONObject gridJsonObj = rootJsonObj.getJSONObject("grid");
			
			Grid grid=gridParser(gridJsonObj);
			
			Date timeRelease= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rootJsonObj.getString("timeRelease"));
			
			
			JSONObject fcst3hourJsonObj= rootJsonObj.getJSONObject("fcst3hour");
			JSONObject fcst6hourJsonObj= rootJsonObj.getJSONObject("fcst6hour");
			
			
			
			FcsPiece fp=new FcsPiece();
			List<FcsPiece> fpList=new LinkedList<>();
			
			
			
			for(int xhourLater=4;xhourLater<=25;xhourLater=xhourLater+3) {
				fp=new FcsPiece();
				fp.setAfterHour(xhourLater);
				
				fp.setWindSpeed(fcst3hourJsonObj.getJSONObject("wind").getDouble("wspd"+xhourLater+"hour"));
				fp.setWindDirection(fcst3hourJsonObj.getJSONObject("wind").getDouble("wdir"+xhourLater+"hour"));
								
				fp.setSkyStatus(fcst3hourJsonObj.getJSONObject("sky").getString("name"+xhourLater+"hour"));
				
				fp.setTemperature(fcst3hourJsonObj.getJSONObject("temperature").getDouble("temp"+xhourLater+"hour"));
				
				fp.setHumidity(fcst3hourJsonObj.getJSONObject("humidity").getDouble("rh"+xhourLater+"hour"));
				
				fp.setRainPer(fcst6hourJsonObj.getString("rain"+(xhourLater-1)*2+"hour"));
				fp.setSnowPer(fcst6hourJsonObj.getString("snow"+(xhourLater-1)*2+"hour"));
				
				fpList.add(fp);
			}
			
			
			
			System.out.println("----------------------------------------");
			System.out.println(grid.toString());
			System.out.println(timeRelease.toString());
			System.out.println(fpList.toString());
			System.out.println("----------------------------------------");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("시간 포맷 timeRelease 파싱에 실패했습니다----------------------------");
		}

	}
	
	private Grid gridParser(JSONObject valueObjJsonObj)  {
		
		try {
			return new Grid(
					valueObjJsonObj.getString("village"),
					valueObjJsonObj.getString("city"), 
					valueObjJsonObj.getString("county"),
					valueObjJsonObj.getDouble("latitude"),
					valueObjJsonObj.getDouble("longitude")
				);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("-----------gridParser에 문제가 있습니다---------",e);
			
			
		}
		return null;


	}
	
	

}

package org.prj.arachne.util.weather;

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
import org.prj.arachne.domain.weather.FcsPiece;
import org.prj.arachne.domain.weather.FcsText;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.valueObj.FcstDaily;
import org.prj.arachne.domain.weather.valueObj.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class SKTWeatherOpenApiUtil {

	private static final String version = "1";
	private static final String foretxt = "Y";

	private static final String appKey = "3a1a26a7-4b40-4b1d-a611-22b12de3c29f";

	@Qualifier("weather")
	private RestTemplate restTemplate;

	@Autowired
	public SKTWeatherOpenApiUtil(RestTemplate restTemplate) {

		this.restTemplate = restTemplate;

	}


	public WeatherForecast requestWeatherForecast(String city, String county, String village) {

		WeatherForecast weatherEntity = null;
		try {
			URI uri = UriComponentsBuilder.newInstance()
						.scheme("https").host("api2.sktelecom.com")
							.path("/weather/forecast/3days")
									.queryParam("version", version)
									.queryParam("city", city)
									.queryParam("county", county)
									.queryParam("village", village)
									.queryParam("foretxt", foretxt)
							.build()
							.encode("UTF-8")
						.toUri();

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("charset", "UTF-8");
			headers.add("content-type", "application/json");
			headers.add("appKey", appKey);

			HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

			log.info("weatherApi uri: "+ uri);

			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

			

			JSONObject rootJsonObj = new JSONObject(responseEntity.getBody().toString());

		

			JSONArray jArr = rootJsonObj.getJSONObject("weather").getJSONArray("forecast3days");

			rootJsonObj = jArr.getJSONObject(0);

			
			
			Grid grid=this.gridParser(rootJsonObj.getJSONObject("grid"), village, city, county);
			
			Date timeRelease= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rootJsonObj.getString("timeRelease"));
			
			
			
			FcsText fctText=this.fcsTestParser(rootJsonObj.getJSONObject("fcstext"));
			FcsText fctTextRegion=this.fcsTestParser(rootJsonObj.getJSONObject("fcstextRegion"));
			
			List<FcsText> fctList=new LinkedList<>();
			fctList.add(fctText);
			fctList.add(fctTextRegion);
						
			List<FcsPiece> fpList=this.fcst3And6hourJsonObjParser(rootJsonObj.getJSONObject("fcst3hour"), 
																		rootJsonObj.getJSONObject("fcst6hour"));
			
			FcstDaily fcstDaily=this.fcstDailyParser(rootJsonObj.getJSONObject("fcstdaily").getJSONObject("temperature"));
		
			
			
			
			weatherEntity=new WeatherForecast();
			weatherEntity.setFcsPieceList(fpList);
			weatherEntity.setGrid(grid);
			weatherEntity.setReleaseTime(timeRelease);
			weatherEntity.setFcstextPair(fctList);
			weatherEntity.setDayMinMax(fcstDaily);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("시간 포맷 timeRelease 파싱에 실패했습니다----------------------------");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return weatherEntity;

	
	}

	public WeatherForecast requestWeatherForecast(String latitude,String longitude) {

		WeatherForecast weatherEntity = null;
		try {
			URI uri = UriComponentsBuilder.newInstance()
					.scheme("https").host("api2.sktelecom.com")
					.path("/weather/forecast/3days")
					.queryParam("version", version)
					.queryParam("lat",latitude)
					.queryParam("lon",longitude)
					.queryParam("foretxt", foretxt)
					.build()
					.encode("UTF-8")
					.toUri();

			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("charset", "UTF-8");
			headers.add("content-type", "application/json");
			headers.add("appKey", appKey);

			HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

			log.info("weatherApi uri: "+ uri);

			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);



			JSONObject rootJsonObj = new JSONObject(responseEntity.getBody().toString());



			JSONArray jArr = rootJsonObj.getJSONObject("weather").getJSONArray("forecast3days");

			rootJsonObj = jArr.getJSONObject(0);



			Grid grid=this.gridParser(rootJsonObj.getJSONObject("grid"), latitude,longitude);

			Date timeRelease= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rootJsonObj.getString("timeRelease"));



			FcsText fctText=this.fcsTestParser(rootJsonObj.getJSONObject("fcstext"));
			FcsText fctTextRegion=this.fcsTestParser(rootJsonObj.getJSONObject("fcstextRegion"));

			List<FcsText> fctList=new LinkedList<>();
			fctList.add(fctText);
			fctList.add(fctTextRegion);

			List<FcsPiece> fpList=this.fcst3And6hourJsonObjParser(rootJsonObj.getJSONObject("fcst3hour"),
					rootJsonObj.getJSONObject("fcst6hour"));

			FcstDaily fcstDaily=this.fcstDailyParser(rootJsonObj.getJSONObject("fcstdaily").getJSONObject("temperature"));




			weatherEntity=new WeatherForecast();
			weatherEntity.setFcsPieceList(fpList);
			weatherEntity.setGrid(grid);
			weatherEntity.setReleaseTime(timeRelease);
			weatherEntity.setFcstextPair(fctList);
			weatherEntity.setDayMinMax(fcstDaily);


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("시간 포맷 timeRelease 파싱에 실패했습니다----------------------------");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return weatherEntity;


	}





	private FcstDaily fcstDailyParser(JSONObject valueObjJsonObj) {
		// TODO Auto-generated method stub
		
		try {
			
			
			return new FcstDaily(
									valueObjJsonObj.getString("tmax1day"), 
									valueObjJsonObj.getString("tmax2day"),
									valueObjJsonObj.getString("tmax3day") ,
									valueObjJsonObj.getString("tmin2day"),
									valueObjJsonObj.getString("tmin2day"),
									valueObjJsonObj.getString("tmin3day")
									);
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		log.error("-----------fcstDailyParser에 문제가 있습니다---------", e);

	}
		
		return null;
	}

	private Grid gridParser(JSONObject valueObjJsonObj,String village,String city,String county) {

		try {
			return new Grid(village, city, county,
					valueObjJsonObj.getDouble("latitude"),
					valueObjJsonObj.getDouble("longitude"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("-----------gridParser에 문제가 있습니다---------", e);

		}
		return null;


	}

	private Grid gridParser(JSONObject valueObjJsonObj,String latitude,String longitude) {

		try {
			return new Grid(valueObjJsonObj.getString("village"),
					valueObjJsonObj.getString("city"),
					valueObjJsonObj.getString("county"),
					Double.parseDouble(latitude),
					Double.parseDouble(longitude));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("-----------gridParser에 문제가 있습니다---------", e);

		}
		return null;


	}

	
	
	private FcsText fcsTestParser(JSONObject valueObjJsonObj) {
		
		try {
			
			
			return new FcsText(null,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueObjJsonObj.getString("timeRelease")),
					valueObjJsonObj.getString("locationName"),
					valueObjJsonObj.getString("text1"));
			
		} catch (ParseException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("fcsTestParser에 문제가 있습니다 -------------------",e);
		}
		
		
		return null;
	}
	
	private List<FcsPiece> fcst3And6hourJsonObjParser(JSONObject fcst3hourJsonObj, JSONObject fcst6hourJsonObj) {

		
			FcsPiece fp ;

			List<FcsPiece> fpList = new LinkedList<>();
		try {
			for (int xhourLater = 4; xhourLater <= 25; xhourLater = xhourLater + 3) {
				fp = new FcsPiece();
				fp.setAfterHour(xhourLater);

				fp.setWindSpeed(fcst3hourJsonObj.getJSONObject("wind").getDouble("wspd" + xhourLater + "hour"));
				fp.setWindDirection(fcst3hourJsonObj.getJSONObject("wind").getDouble("wdir" + xhourLater + "hour"));

				fp.setSkyStatus(fcst3hourJsonObj.getJSONObject("sky").getString("name" + xhourLater + "hour"));

				fp.setTemperature(
						fcst3hourJsonObj.getJSONObject("temperature").getDouble("temp" + xhourLater + "hour"));

				fp.setHumidity(fcst3hourJsonObj.getJSONObject("humidity").getDouble("rh" + xhourLater + "hour"));

				fp.setRainPer(fcst6hourJsonObj.getString("rain" + (xhourLater - 1) * 2 + "hour"));
				fp.setSnowPer(fcst6hourJsonObj.getString("snow" + (xhourLater - 1) * 2 + "hour"));

				fpList.add(fp);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("---------fcst3And6hourJsonObjParser 파싱 오류 입니다 ------------------", e);
		}
		
		
		
		
		
		return fpList;
	}
	
}

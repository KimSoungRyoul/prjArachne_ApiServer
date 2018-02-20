package org.prj.arachne.util.weather.pastWeather;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.prj.arachne.util.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
public class WeatherOpenApiUtil {

	private static final String apiKey="qmaIwz9MKYURW4O%2BGRV3V8YTOuBlXnjzb3xwqqlUx2ZFLKhcBx2XhDudC9fNM9Ry";
	
	private String defaultUrl="http://data.kma.go.kr/apiData/getData";
	
	@Autowired
	@Qualifier("unsafe")
	private RestTemplate rest;

	
	public List<WeatherDTO> requestWeather(String startDt,String endDt,int stnIds) {
	
		String url = null;
		try {
			url = defaultUrl+"?type=json&dataCd=ASOS&dateCd=DAY&schListCnt=10&pageIndex=1"
					+"&stnIds="+stnIds
					+ "&startDt="+startDt
					+"&endDt="+endDt
					+ "&apiKey=" + URLDecoder.decode(apiKey, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FailWeatherApiURLBuildException("weatherAPI url을 빌드하는데 실패하였습니다");
		}

		
		

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("charset", "UTF-8");
		headers.add("content-type", "application/json");
		HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);

		ResponseEntity<String> entity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class);
		
		log.debug(entity.getBody().toString());
		
		JSONArray jArrOrigin=null;
		JSONObject jObj=null;
		WeatherDTO dto=null;
		List<WeatherDTO> dtos=new ArrayList<>();
		try {
			jArrOrigin = new JSONArray(entity.getBody());
		
		
		
		
		
		
			jObj=jArrOrigin.getJSONObject(3);
			
			JSONArray jArr= jObj.getJSONArray("info");
		
			for(int i=0; i<jArr.length();i++) {
				
				dto=WeatherDTO.builder()
						.avg_cm5_te(jArr.getJSONObject(i).getString("AVG_CM5_TE"))
						.avg_rhn(jArr.getJSONObject(i).getString("AVG_RHM"))
						.avg_ws(jArr.getJSONObject(i).getString("AVG_WS"))
						//.dd_mes(jArr.getJSONObject(i).getString("DD_MEFS"))
						.max_ta(jArr.getJSONObject(i).getString("MAX_TA"))
						.min_ta(jArr.getJSONObject(i).getString("MIN_TA"))
						.stn_nm(jArr.getJSONObject(i).getString("STN_NM"))
						//.sum_rn(jArr.getJSONObject(i).getString("SUM_RN"))
						.build();
				dtos.add(dto);
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			log.info("WeatherOpenAPI JSON데이터를 파싱하는 과정에서 실패했습니다...");
			e.printStackTrace();
			
		}
		
		
		
		return dtos;

		

	}


}

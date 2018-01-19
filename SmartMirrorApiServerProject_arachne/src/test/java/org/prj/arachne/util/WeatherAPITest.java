package org.prj.arachne.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(JUnit4.class)
public class WeatherAPITest {

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testWetherAPI_GET() throws UnsupportedEncodingException {

		String url = "http://data.kma.go.kr/apiData/getData";

		URI uri = UriComponentsBuilder.fromHttpUrl("http://data.kma.go.kr/apiData/getData")
					.queryParam("type", "json")
					.queryParam("dataCd","ASOS")
					.queryParam("dateCd","DAY")
					.queryParam("startDt","20180117")
					.queryParam("endDt","20180117")
					.queryParam("stnIds","108")
					.queryParam("schListCnt","10")
					.queryParam("pageIndex","1")
					.queryParam("apiKey","%2BJMXqv4Z4G2Kvo%2BR3tTP9Kdvf6hQ/f8K74cVdrR0xPBFIFjdItMhZtyuiSuLf5Ge")
				
					.build()
				.encode("utf-8")
				.toUri();
		

		

		System.out.println(uri.toString());
		
		
		System.out.println("%2BJMXqv4Z4G2Kvo%2BR3tTP9Kdvf6hQ/f8K74cVdrR0xPBFIFjdItMhZtyuiSuLf5Ge");
		
		/*Map<String, String> apiParams = new HashMap<>();
		apiParams.put("type", "json");
		apiParams.put("dataCd", "ASOS");
		apiParams.put("dateCd", "DAY");
		apiParams.put("startDt", "20180118");
		apiParams.put("endDt", "20180118");
		apiParams.put("stnIds", "108");
		apiParams.put("schListCnt", "10");
		apiParams.put("pageIndex", "1");
		apiParams.put("apiKey", "%2BJMXqv4Z4G2Kvo%2BR3tTP9Kdvf6hQ/f8K74cVdrR0xPBFIFjdItMhZtyuiSuLf5Ge");*/

		/*ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, apiParams);

		System.out.println(entity.getBody().toString());*/

	}

}

package org.prj.arachne;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.util.UriComponentsBuilder;

public class TestInMain {

	public static void main(String[] args) throws URISyntaxException {
			
		String version="1";
		//String latitude="";
		//String longtitude;
		String city="서울";
		String county="중랑구";
		String vilage="상봉1동";
		String foretxt="Y";
		
		
		URI uri=UriComponentsBuilder.newInstance()
				.scheme("https").host("api2.sktelecom.com").path("/weather/forecast/3days")
				.queryParam("version", "1")
				.queryParam("city",city)
				.queryParam("county", county)
				.queryParam("vilage", vilage)
				.queryParam("foretxt", foretxt)
				.build()
				.toUri();
		
		System.out.println(uri);
		
		
	}
}

package org.prj.arachne;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;
import org.prj.arachne.domain.fileinfo.valueObj.FileType;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.MemberAuthority;
import org.prj.arachne.domain.member.MemberInfo;
import org.prj.arachne.domain.member.valueObj.AuthorityType;
import org.prj.arachne.domain.member.valueObj.Gender;
import org.prj.arachne.domain.member.valueObj.Password;
import org.prj.arachne.domain.member.valueObj.PhysicalInfo;
import org.prj.arachne.domain.member.valueObj.PhysicalType;
import org.prj.arachne.presentation.dto.AuthenticationRequest;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

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

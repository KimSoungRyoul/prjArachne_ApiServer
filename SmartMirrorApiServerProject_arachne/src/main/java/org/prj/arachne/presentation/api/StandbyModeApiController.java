package org.prj.arachne.presentation.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.prj.arachne.application.OpenApiService;
import org.prj.arachne.domain.weather.SimpleWeather;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.prj.arachne.util.dto.WeatherDTO;
import org.prj.arachne.util.weather.pastWeather.WeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;


@Api(value="날씨 API",description="날씨관련 Api입니다(기상청+ SKTOpenApi사용)")
@RestController
@Log4j
public class StandbyModeApiController implements Version1ApiMapping{

	private WeatherOpenApiUtil wApi;
	
	private OpenApiService oaService;
	

	@Autowired
	public StandbyModeApiController(WeatherOpenApiUtil wApi, OpenApiService oaService) {
		this.wApi = wApi;
		this.oaService = oaService;
	}

	
	

	@ApiOperation(value="SKTWeatherOpenApi로 조회한 날씨정보, 3시간 단위 갱신됩니다(주소기반)",response=WeatherForecast.class,produces="application/json")
	@ApiImplicitParams({
		@ApiImplicitParam(name="city",value="도,시 빼고 입력하기 ex)서울시(x),서울(o),경기(o) 이거는 틀리면 아예 데이터 안줌",paramType="path"),
		@ApiImplicitParam(name="county",value="ex)중랑구,시흥시",paramType="path"),
		@ApiImplicitParam(name="village",value="ex) 정왕동, 상봉1동,vilage는 정확하지 않아도 어느정도 api내부에서 좌표 수정합니다.",paramType="path")
			,@ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
	})
	@GetMapping("/weather/{city}/{county}/{village}")
	public ResponseEntity<Map<String, Object>> sktWeather(@PathVariable("city") String city,
				@PathVariable("county") String county, @PathVariable("village") String village){
		
		log.info("city:"+city+ "]]  county: "+county+"]] village: "+village);
		
			ResponseEntity<Map<String, Object>> entity=null;
		
			
		 	Map<String, Object> values=new HashMap<>();
	 	
		 	WeatherForecast wf=oaService.requestwForecast(city, county, village);
		 	
		 	StatusEntity status=new StatusEntity();
		 	status.setApiType("SKT 기상관측자료 OpenApi");
		 	status.setMessage("SUCCSS");
		 	status.setStatusCode(ArachneStatus.SUCCESS);
		 	
		 	
		 	
		 	values.put("status", status);
		 	values.put("entity", wf);
		 	
		 	
		 	
		 	entity=new ResponseEntity<>(values,HttpStatus.OK);
		
	
		
		
		
		
		return entity;
	}


	@ApiOperation(value="간단 날씨정보 조회, (좌표기반)",response=SimpleWeather.class,produces="application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name="latitude",value="좌표정보 lat",dataType = "String",paramType="query"),
			@ApiImplicitParam(name="longitude",value="좌표정보 lon",dataType = "String",paramType="query"),
			@ApiImplicitParam(name="memberId",value = "회원시리얼넘버",dataType = "Long",paramType = "path"),
			@ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
	})
	@GetMapping("/weather/simple/{memberId}")
	public ResponseEntity<Map<String, Object>> sktSimpleWeather(@RequestParam("latitude")String latitude,
														  @RequestParam("longitude") String longitude,
																@PathVariable("memberId")Long memberId) {


		ResponseEntity<Map<String, Object>> entity=null;


		Map<String, Object> values=new HashMap<>();

		SimpleWeather wf=oaService.requestwSimpleWeatherForecast(latitude, longitude,memberId);

		StatusEntity status=new StatusEntity();
		status.setApiType("SKT 기상관측자료 OpenApi");
		status.setMessage("simple Weather 입니다.");
		status.setStatusCode(ArachneStatus.SUCCESS);



		values.put("status", status);
		values.put("entity", wf);



		entity=new ResponseEntity<>(values,HttpStatus.OK);






		return entity;






	}




		@ApiOperation(value="SKTWeatherOpenApi로 조회한 날씨정보, 3시간 단위 갱신됩니다(좌표기반)",response=WeatherForecast.class,produces="application/json")
	@ApiImplicitParams({
			@ApiImplicitParam(name="latitude",value="좌표정보 lat",dataType = "String",paramType="query"),
			@ApiImplicitParam(name="longitude",value="좌표정보 lon",dataType = "String",paramType="query")
			,@ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
	})
	@GetMapping("/weather")
	public ResponseEntity<Map<String, Object>> sktWeather(@RequestParam("latitude")String latitude,@RequestParam("longitude") String longitude){


		ResponseEntity<Map<String, Object>> entity=null;


		Map<String, Object> values=new HashMap<>();

		WeatherForecast wf=oaService.requestwForecast(latitude,longitude);

		StatusEntity status=new StatusEntity();
		status.setApiType("SKT 기상관측자료 OpenApi");
		status.setMessage("SUCCSS");
		status.setStatusCode(ArachneStatus.SUCCESS);



		values.put("status", status);
		values.put("entity", wf);



		entity=new ResponseEntity<>(values,HttpStatus.OK);






		return entity;
	}









	
	@ApiOperation(value="기상청 OpenApi 활용한 '과거'날씨 정보들 (사용 안함)")
	@GetMapping("/weather/{placeName}")
	public ResponseEntity<Map<String, Object>> weather(@PathVariable("placeName") String place
														,@RequestParam("startDt") String startDt
														,@RequestParam("endDt") String endDt
														){
		
		ResponseEntity<Map<String, Object>> entity=null;
		
	 List<WeatherDTO> weatehrDTOs=	wApi.requestWeather(startDt, endDt, 108);
		
	 	Map<String, Object> values=new HashMap<>();
	 	
	 	StatusEntity status=new StatusEntity();
	 	status.setApiType("기상청관측자료(종관 일자료)OpenApi");
	 	status.setMessage("SUCCSS");
	 	status.setStatusCode(ArachneStatus.SUCCESS);
	 	
	 	values.put("status", status);
	 	values.put("weathers", weatehrDTOs);
	 	
	 	
	 	
	 	entity=new ResponseEntity<>(values,HttpStatus.OK);
	 
		
	
	 return entity;
		
	 
	}
	
	
	
}

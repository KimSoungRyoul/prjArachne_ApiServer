package org.prj.arachne.application;

import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.repository.FcsTextRepository;
import org.prj.arachne.domain.weather.repository.FscPieceRepository;
import org.prj.arachne.domain.weather.repository.WeatherForecastRepositroy;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class OpenApiService {

	@Autowired
	private SKTWeatherOpenApiUtil sktwApi;

	@Autowired
	private WeatherForecastRepositroy wfRepo;

	@Autowired
	private FscPieceRepository fpRepo;

	@Autowired
	private FcsTextRepository ftRepo;

	@Transactional
	public WeatherForecast requestwForecast(String city, String county, String village) {

		WeatherForecast wf;

		wf = wfRepo.findTop1ByGridCityAndGridCountryAndGridVillageOrderByReleaseTime(city, county, village);


		if (wf == null) {
			log.info("db에 저장된 날씨정보가 없습니다 Api를 통해 데이터를 가져옵니다......");
			wf = sktwApi.requestWeatherForecast(city, county, village);
			
		}else {
			wf.getFcsPieceList();
			wf.getFcstextPair();
			
		}
		return wf;
	}

	@Transactional
	public void registerWeatherForcaset(WeatherForecast wf) {

		fpRepo.save(wf.getFcsPieceList());
		ftRepo.save(wf.getFcstextPair());

		wfRepo.save(wf);

	}

}

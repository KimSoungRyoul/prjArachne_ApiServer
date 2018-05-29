package org.prj.arachne.application;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.weather.SimpleWeather;
import org.prj.arachne.domain.weather.WeatherForecast;
import org.prj.arachne.domain.weather.repository.FcsTextRepository;
import org.prj.arachne.domain.weather.repository.FscPieceRepository;
import org.prj.arachne.domain.weather.repository.SimpleWeatherRepository;
import org.prj.arachne.domain.weather.repository.WeatherForecastRepositroy;
import org.prj.arachne.util.weather.SKTWeatherOpenApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Log4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class OpenApiService {


    private SKTWeatherOpenApiUtil sktwApi;


    private WeatherForecastRepositroy wfRepo;


    private FscPieceRepository fpRepo;


    private FcsTextRepository ftRepo;

    private SimpleWeatherRepository simpleWeatherRepository;


    @Transactional
    public SimpleWeather requestwSimpleWeatherForecast(String lat, String lon, Long memberId) {

        SimpleWeather simpleWeather = null;

        simpleWeather = simpleWeatherRepository.findTopByGridLatitudeAndGridLongtitudeAndWeatherOwnerMemberId(Double.parseDouble(lat), Double.parseDouble(lon), memberId);


        if (simpleWeather == null) {
            log.info("simpleWeather::: db에 저장된 날씨정보가 없습니다 Api를 통해 데이터를 가져옵니다......");
            long minute = 0L;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


                Date reqDate = simpleWeather.getReleaseTime();
                long reqDateTime = reqDate.getTime(); //현재시간을 요청시간의 형태로 format 후 time 가져오기
                Date curDate = dateFormat.parse(dateFormat.format(new Date()));
                long curDateTime = curDate.getTime(); //분으로 표현
                minute = (curDateTime - reqDateTime) / 60000;


            } catch (ParseException e) {
                e.printStackTrace();
            }


            if (minute > 60) {
                simpleWeather = sktwApi.requestSimpleWeatherForecaset(lat, lon);
                simpleWeather.setWeatherOwner(new MemberAccount(memberId));
                simpleWeatherRepository.save(simpleWeather);
            }
        }

        return simpleWeather;


    }


    @Transactional
    public WeatherForecast requestwForecast(String city, String county, String village) {

        WeatherForecast wf;

        wf = wfRepo.findTop1ByGridCityAndGridCountryAndGridVillageOrderByReleaseTime(city, county, village);


        if (wf == null) {
            log.info("db에 저장된 날씨정보가 없습니다 Api를 통해 데이터를 가져옵니다......");

            wf = sktwApi.requestWeatherForecast(city, county, village);
            fpRepo.save(wf.getFcsPieceList());
            ftRepo.save(wf.getFcstextPair());
            wfRepo.save(wf);
        } else {
            wf.getFcsPieceList();
            wf.getFcstextPair();

        }
        return wf;
    }

    @Transactional
    public WeatherForecast requestwForecast(String latitude, String longitude) {

        WeatherForecast wf;

        wf = wfRepo.findTop1ByGridLatitudeAndGridLongtitude(Double.parseDouble(latitude), Double.parseDouble(longitude));


        if (wf == null) {
            log.info("db에 저장된 날씨정보가 없습니다 Api를 통해 데이터를 가져옵니다......");
            wf = sktwApi.requestWeatherForecast(latitude, longitude);

        } else {
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

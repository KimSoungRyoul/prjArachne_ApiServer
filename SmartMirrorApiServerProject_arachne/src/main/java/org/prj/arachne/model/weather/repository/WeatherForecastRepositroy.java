package org.prj.arachne.model.weather.repository;

import org.prj.arachne.model.weather.WeatherForecast;
import org.springframework.data.repository.CrudRepository;

public interface WeatherForecastRepositroy extends CrudRepository<WeatherForecast, Long>{

	public WeatherForecast findTop1ByGridCityAndGridCountryAndGridVillageOrderByReleaseTime(String city,String county,String village);

	public WeatherForecast findTop1ByGridLatitudeAndGridLongtitude(Double gridLatitude,Double gridLongtitude);



}

package org.prj.arachne.domain.weather.repository;

import org.prj.arachne.domain.weather.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherForecastRepositroy extends JpaRepository<WeatherForecast, Long>{

	public WeatherForecast findTopTimeReleaseByGridCityAndGridCountryAndGridVillage(String city,String county,String village);
}

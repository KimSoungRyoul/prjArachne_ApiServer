package org.prj.arachne.domain.weather.repository;

import org.prj.arachne.domain.weather.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface WeatherForecastRepositroy extends CrudRepository<WeatherForecast, Long>{

	public WeatherForecast findTop1ByGridCityAndGridCountryAndGridVillageOrderByReleaseTime(String city,String county,String village);




}

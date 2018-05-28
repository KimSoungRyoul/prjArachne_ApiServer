package org.prj.arachne.model.weather.repository;

import org.prj.arachne.model.weather.SimpleWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleWeatherRepository extends JpaRepository<SimpleWeather,Long> {

    SimpleWeather findTopByGridLatitudeAndGridLongtitudeAndWeatherOwnerMemberId(double latitude,double longtitude,Long weatherOwnerId);

}

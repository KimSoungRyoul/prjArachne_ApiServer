package org.prj.arachne.domain.weather.valueObj.simpleWeather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prj.arachne.domain.weather.valueObj.PrecipitationType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Precipitation {

    private String sinceOnTime;

    @Enumerated(EnumType.STRING)
    private PrecipitationType precipitationType;

}

package org.prj.arachne.model.weather;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.prj.arachne.model.member.MemberAccount;
import org.prj.arachne.model.weather.valueObj.Grid;
import org.prj.arachne.model.weather.valueObj.simpleWeather.Precipitation;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "간단 날씨정보 모델")
@Data
public class SimpleWeather {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;


    @ManyToOne
    @JoinColumn(name="weather_owner_id")
    @JsonIgnore
    private MemberAccount weatherOwner;


    //기상예보 위치
    @Embedded
    @ApiModelProperty(value = "지역정보",dataType = "Grid")
    private Grid grid;


    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
    private Date releaseTime;

    //강수량
    @Embedded
    @ApiModelProperty(value = "강수량 관련")
    private Precipitation precipitation;

    //하늘 상태
    private String skyState;

    private String temperatureToday;
    private String temperatureMax;
    private String temperatureMin;

    private String humidity;

    //바람방향 세기

    private String windirection;
    private String windspeed;


}

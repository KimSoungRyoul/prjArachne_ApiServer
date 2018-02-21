package org.prj.arachne.util.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
public class WeatherDTO {

	private String stn_nm; // 지명(서울)
	private String avg_rhn; // 평균 상대 습도
	private String avg_cm5_te; // 평균  온도
	private String min_ta; // 최저 기온
	private String max_ta; // 최고기온
	private String avg_ws; // 평균 풍속
	private String sum_rn; // 일강수량
	private String dd_mes; // 일 최심적설
	
}

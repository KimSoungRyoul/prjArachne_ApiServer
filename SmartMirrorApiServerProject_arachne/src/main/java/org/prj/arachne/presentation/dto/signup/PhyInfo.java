package org.prj.arachne.presentation.dto.signup;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="회원 신체 정보")
public class PhyInfo{

	@ApiModelProperty(dataType="int",example="181")
	private int height;

	@ApiModelProperty(dataType="int",example="65")
	private int weight;

	@ApiModelProperty(dataType="String",example="SLIM")
	private String pType;
	
}
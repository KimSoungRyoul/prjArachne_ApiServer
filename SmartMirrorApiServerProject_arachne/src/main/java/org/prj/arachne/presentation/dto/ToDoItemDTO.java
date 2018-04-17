package org.prj.arachne.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description="ToDoItem")
public class ToDoItemDTO {

    @ApiModelProperty(dataType = "Long",example = "1")
    private Long id;

    @ApiModelProperty(dataType = "Date",example = "2018-04-16 21:12:49")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date date;

    @ApiModelProperty(dataType = "String",example = "제목입니다.글자제한225")
    private String title;

    @ApiModelProperty(dataType = "String", example = "상식적인 크기선에서 글자제한 걱정없음")
    private String contents;


}

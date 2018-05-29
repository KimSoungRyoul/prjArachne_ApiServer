package org.prj.arachne.presentation.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.prj.arachne.application.ScheduleService;
import org.prj.arachne.domain.Schedule.ToDoItem;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.prj.arachne.presentation.dto.ToDoItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "ToDoList 관련 Api", description = "ToDoList 관련 Api")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleApiController implements Version1ApiMapping {

    private ScheduleService scheduleService;





    @ApiOperation(value = "ToDoList요청",response=ToDoItem.class, produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberSerialNum", value = "해당Item을소유한 회원고유번호", required = true, paramType="path", dataType = "Long", defaultValue = ""),
            @ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
    })
    @GetMapping("/schedule/{memberSerialNum}")
    public ResponseEntity<Map<String, Object>> GETScheduleList(@PathVariable("memberSerialNum") Long memberSerialNum) {

        ResponseEntity<Map<String, Object>> entity = null;


            List<ToDoItem> toDoItems=scheduleService.requestToDOList(memberSerialNum);
            for(ToDoItem todoItem: toDoItems){
                todoItem.setItemOwner(null);
            }


        Map<String, Object> values = new HashMap<>();
        values.put("entity", toDoItems);
        values.put("status", new StatusEntity("Schedule Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));

        entity = new ResponseEntity<>(values, HttpStatus.OK);

        return entity;
    }

    @ApiOperation(value = "ToDoItem 저장 요청",response=StatusEntity.class, produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberSerialNum", value = "해당Item을소유한 회원고유번호", required = true, paramType="path", dataType = "Long", defaultValue = ""),
            @ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
    })
    @PostMapping("/schedule/{memberSerialNum}")
    public ResponseEntity<Map<String, Object>> saveInRemoteStorage(@PathVariable("memberSerialNum") Long memberSerialNum,
                                                                   @ApiParam(name = "toDoItem", value = "할일목록 ,날짜포맷 공백,':' 정확히지키세요" ,required = true )
                                                                   @RequestBody ToDoItemDTO toDoItemDTO) {


        ResponseEntity<Map<String, Object>> entity = null;

        ToDoItem toDoItem=new ToDoItem(null,new MemberAccount(memberSerialNum),toDoItemDTO.getDate(),toDoItemDTO.getTitle(),toDoItemDTO.getContents());

        scheduleService.registerToDoItem(toDoItem);

        Map<String, Object> values = new HashMap<>();

        values.put("status", new StatusEntity("Schedule Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));



        entity = new ResponseEntity<Map<String, Object>>(values, HttpStatus.CREATED);

        return entity;

    }

    @ApiOperation(value = "ToDoItem(todoItemId에 맞는 데이터를) 수정 요청",response=StatusEntity.class, produces="application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "memberSerialNum", paramType = "path",value = "해당Item을 소유한 회원고유번호",dataType = "Long",defaultValue = "1"),
        @ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
    })
    @PutMapping("/schedule/{memberSerialNum}")
    public ResponseEntity<Map<String, Object>> modifySchedule(@PathVariable("memberSerialNum")Long memberSerialNum,
            @ApiParam(name = "toDoItem", value = "할일목록 ,날짜포맷 지키세요" ,required = true )
            @RequestBody ToDoItemDTO toDoItemDTO) {

        ResponseEntity<Map<String, Object>> entity = null;

        ToDoItem toDoItem=new ToDoItem(toDoItemDTO.getId(),new MemberAccount(memberSerialNum),
                                        toDoItemDTO.getDate(),toDoItemDTO.getTitle(),toDoItemDTO.getContents());

        scheduleService.modifyToDoItem(toDoItem);
        Map<String, Object> values = new HashMap<>();

        values.put("status", new StatusEntity("Schedule Api", ArachneStatus.SUCCESS, "수정 요청 성공했습니다"));

        entity = new ResponseEntity<Map<String, Object>>(values, HttpStatus.OK);

        return entity;


    }

    @ApiOperation(value = "ToDoItem 1개 삭제 요청",response=ToDoItem.class, produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "todoItemId", value = "todoItem의 고유번호 (회원고유번호 아닙니다!!)", required = true, paramType="path", dataType = "Long", defaultValue = ""),
            @ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
    })
    @DeleteMapping("/schedule/{todoItemId}")
    public ResponseEntity<Map<String, Object>> removeOneMemo(@PathVariable("todoItemId") Long todoItemId) {

        ResponseEntity<Map<String, Object>> entity = null;

        scheduleService.deleteToDoItemList(todoItemId);
        Map<String, Object> values = new HashMap<>();

        values.put("status", new StatusEntity("Schedule Api", ArachneStatus.SUCCESS, "삭제 요청 성공했습니다"));

        entity = new ResponseEntity<Map<String, Object>>(values, HttpStatus.ACCEPTED);

        return entity;


    }


}

package org.prj.arachne.presentation.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.prj.arachne.application.MirrorSettingService;
import org.prj.arachne.model.member.MemberMirrorSettingInfo;
import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.MirrorSettingDTO;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(description = "거울설정값변경또는푸쉬 API")
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class MirrorSettingInfoApiController implements Version1ApiMapping {

    private MirrorSettingService mirrorSettingService;


    @ApiOperation(value = "거울세팅정보를 가져옵니다.", produces = "application/json", response = MemberMirrorSettingInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberSerialNum", value = "회원고유번호", paramType = "path", dataType = "Long"),
            @ApiImplicitParam(name = "x-auth-token", value = "인증토큰", required = true, paramType = "header", dataType = "string")
    }
    )
    @GetMapping("/mirrorsetting/{memberSerialNum}")
    public ResponseEntity<Map<String, Object>> GETSettingInfo(@PathVariable("memberSerialNum") Long memberId) {

        ResponseEntity<Map<String, Object>> response = null;

        Map<String, Object> values = new HashMap<>();

        MemberMirrorSettingInfo settingInfo = mirrorSettingService.requestMirrorSetting(memberId);

        values.put("entity", settingInfo);
        values.put("status", new StatusEntity("MirrorSetting Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));

        response = new ResponseEntity<>(values, HttpStatus.OK);

        return response;

    }

    @ApiOperation(value = "거울세팅정보 변경.", produces = "application/json", response = ArachneStatus.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x-auth-token", value = "인증토큰", required = true, paramType = "header", dataType = "string")
    }
    )
    @PutMapping("/mirrorsetting/{memberSerialNum}")
    public ResponseEntity<Map<String, Object>> PUTSettingInfo(@PathVariable("memberSerialNum")Long memberSerialNum, @RequestBody MirrorSettingDTO mirrorSettingDTO) {

        ResponseEntity<Map<String, Object>> response = null;

        Map<String, Object> values = new HashMap<>();

        mirrorSettingService.modifyMirrorSetting(mirrorSettingDTO,memberSerialNum);


        values.put("status", new StatusEntity("MirrorSetting Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));

        response = new ResponseEntity<>(values, HttpStatus.OK);


        return response;
    }


}

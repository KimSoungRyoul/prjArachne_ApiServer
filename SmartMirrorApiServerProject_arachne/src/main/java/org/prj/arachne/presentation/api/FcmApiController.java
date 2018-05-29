package org.prj.arachne.presentation.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.prj.arachne.domain.member.MemberAccount;
import org.prj.arachne.domain.member.repository.MemberAccountRepository;
import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.presentation.dto.ArachneStatus;
import org.prj.arachne.presentation.dto.FCMessageDTO;
import org.prj.arachne.presentation.dto.StatusEntity;
import org.prj.arachne.util.fcm.FCMUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j
@Api(value = "fcm을 위해 간접적으로 쓰이는 api")
public class FcmApiController implements Version1ApiMapping {

  @Autowired
  private FCMUtil fcmUtil;

  @Autowired
  private MemberAccountRepository memberAccountRepository;

  @PostMapping("/fcm/token")
  @ApiOperation(value = "메시지push를 위한 redirectoken서버에 저장 이값은 아래 test의 to값에 해당합니다 ",produces = "application/json")
  public ResponseEntity<Map<String,Object>> postToken(@ModelAttribute("token") String token,@ModelAttribute("userId")String userId){


    ResponseEntity<Map<String, Object>> entity=null;

    log.info(token);
    MemberAccount memberAccount= memberAccountRepository.findByEmail(userId);
    memberAccount.setFcmRedirectToken(token);
    memberAccountRepository.save(memberAccount);

    Map<String, Object> values=new HashMap<>();
    values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));
    entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.CREATED);
    return entity;
  }

  @PostMapping("/test/fcm/message")
  @ApiOperation(value = "push테스트용 ",produces = "application/json")
  public ResponseEntity<Map<String,Object>> postToken(@RequestBody FCMessageDTO fcMessageDTO){

    fcmUtil.pushMessage(fcMessageDTO);

    ResponseEntity<Map<String, Object>> entity=null;

    Map<String, Object> values=new HashMap<>();
    values.put("status", new StatusEntity("MemberInfo Api", ArachneStatus.SUCCESS, "정상적인 요청 입니다"));

    entity=new ResponseEntity<Map<String,Object>>(values, HttpStatus.OK);


    return entity;
  }

}

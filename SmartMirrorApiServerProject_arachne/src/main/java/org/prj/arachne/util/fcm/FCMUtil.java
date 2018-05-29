package org.prj.arachne.util.fcm;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.prj.arachne.presentation.dto.FCMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j
public class FCMUtil {

  @Autowired
  @Qualifier("fcm")
  private RestTemplate restTemplate;


  @Autowired
  @Qualifier("fcm")
  private String serverKey;

  private String url="https://fcm.googleapis.com/fcm/send";


  public int pushMessage(FCMessageDTO fcMessageDTO){

    HttpHeaders headers=new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    headers.set("Authorization",serverKey);

    HttpEntity requestEntity =  new HttpEntity(new Gson().toJson(fcMessageDTO), headers);

    ResponseEntity<String> responseEntity = restTemplate.exchange(url,HttpMethod.POST,requestEntity,String.class);

    log.info(responseEntity.getBody());

    return new JSONObject(responseEntity.getBody()).getInt("success");


  }

}

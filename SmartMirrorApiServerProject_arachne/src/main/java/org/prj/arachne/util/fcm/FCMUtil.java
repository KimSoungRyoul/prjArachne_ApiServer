package org.prj.arachne.util.fcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FCMUtil {

  @Autowired
  private RestTemplate restTemplate;


  public void pushMessage(String message){



  }

}

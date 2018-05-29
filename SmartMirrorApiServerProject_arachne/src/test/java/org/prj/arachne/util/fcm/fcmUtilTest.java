package org.prj.arachne.util.fcm;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.prj.arachne.presentation.dto.FCMessageDTO;
import org.prj.arachne.presentation.dto.FCMessageDTO.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/*

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Log4j
public class fcmUtilTest {

  @Autowired
  private FCMUtil fcmUtil;


  @Test
  public void fcmTest(){

    FCMessageDTO fcMessageDTO=new FCMessageDTO();
    fcMessageDTO.setTo("dBbekBDi8Q0:APA91bFK6TDHICKwJ2IUGBUeKHUoUewoGeqX-prgP1NesOoAER8tkQlzXYwZT4ZWwqdgB4no63TymV_br1UC0PwTgui9T3dyA2RaoiKreQze0bLpNz3sTfxMrvQgfQuV4VtO20O8GtDE");

    Notification notification =new FCMessageDTO.Notification();
    notification.setTitle("titleHello");
    notification.setBody("this is body");

    fcMessageDTO.setNotification(notification);



    String isSuccess= fcmUtil.pushMessage(fcMessageDTO);

    log.info("IsSuccess : "+isSuccess);
  }


}
*/

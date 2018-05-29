package org.prj.arachne.presentation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FCMessageDTO {

  private Notification notification;

  private String to;


  @Data
  @NoArgsConstructor
  public static class Notification{
    String title;
    String body;

  }


}

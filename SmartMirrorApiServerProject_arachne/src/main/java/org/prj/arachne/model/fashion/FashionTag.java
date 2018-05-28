package org.prj.arachne.model.fashion;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fashion_tag")
@Data
public class FashionTag {

  @Id
  private String id;

  private String img_path;

  private Map<String,Double> tags=new HashMap<>();

}

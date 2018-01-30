package org.prj.arachne;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;
import org.prj.arachne.domain.fileinfo.valueObj.FileType;
import org.prj.arachne.presentation.dto.AuthenticationRequest;

import com.google.gson.Gson;

public class TestInMain {

	public static void main(String[] args) throws URISyntaxException {
		String originalFileName ="testImageFile.jpg";
		
		System.out.println(originalFileName
						.substring(originalFileName.indexOf(".")+1, originalFileName.length()).toUpperCase());
		
		System.out.println(FileType.valueOf(originalFileName
						.substring(originalFileName.indexOf(".")+1, originalFileName.length()).toUpperCase()));
		
		
	
		
		System.out.println(new Date());
		
		JSONObject jObj=new JSONObject();
		
		try {
			jObj.put("regDate", new Date());
			
			System.out.println(jObj.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		System.out.println(currentDateTime);
	}
}

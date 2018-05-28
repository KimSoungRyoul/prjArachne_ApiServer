package org.prj.arachne.util.mail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMailhtml {

	
	
	public static void main(String[] args) throws  Exception{

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


		Date reqDate = dateFormat.parse("2018-05-13 14:00:00");
		long reqDateTime = reqDate.getTime(); //현재시간을 요청시간의 형태로 format 후 time 가져오기
		Date curDate = dateFormat.parse(dateFormat.format(new Date()));
		long curDateTime = curDate.getTime(); //분으로 표현
		long minute = (curDateTime - reqDateTime) / 60000;
		System.out.println(minute);

		System.out.println(new Date().toString());


		
	}
}

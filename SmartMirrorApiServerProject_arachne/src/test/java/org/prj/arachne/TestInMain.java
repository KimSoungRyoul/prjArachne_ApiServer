package org.prj.arachne;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.prj.arachne.presentation.dto.AuthenticationRequest;

import com.google.gson.Gson;

public class TestInMain {

	public static void main(String[] args) throws URISyntaxException {
		
		URI uri=new URI("http://data.kma.go.kr/apiData/getData?type=json&dataCd=ASOS&dateCd=DAY&startDt=20100101&endDt=20100102&stnIds=108&schListCnt=10&pageIndex=1&apiKey=qmaIwz9MKYURW4O%2BGRV3V8YTOuBlXnjzb3xwqqlUx2ZFLKhcBx2XhDudC9fNM9Ry");
		
		System.out.println(uri);
		
		
	}
}

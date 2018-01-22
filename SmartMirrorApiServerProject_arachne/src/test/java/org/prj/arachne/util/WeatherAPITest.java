package org.prj.arachne.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.prj.arachne.configuration.JsonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.annotations.JsonAdapter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

@RunWith(SpringRunner.class)
// @SpringBootTest
public class WeatherAPITest {

	// @Autowired

	private RestTemplate restTemplate ;

	

	// @Test
	public void noSSL() throws Exception {

		String urlStr = "http://data.kma.go.kr/apiData/getData?type=json&dataCd=ASOS&dateCd=DAY&startDt=20100101&endDt=20100102&stnIds=108&schListCnt=10&pageIndex=1&apiKey=qmaIwz9MKYURW4O%2BGRV3V8YTOuBlXnjzb3xwqqlUx2ZFLKhcBx2XhDudC9fNM9Ry";
		StringBuffer sb = new StringBuffer();
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			System.out.println(sb.toString());
			br.close();
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	//@Test
	public void okhttp3() throws Exception {

		OkHttpClient client = getUnsafeOkHttpClient();

		Request request = new Request.Builder()
				.url("http://data.kma.go.kr/apiData/getData?type=json&dataCd=ASOS&dateCd=DAY"
						+ "&startDt=20100101&endDt=20100102&stnIds=108&schListCnt=10&pageIndex=1"
						+ "&apiKey=qmaIwz9MKYURW4O%2BGRV3V8YTOuBlXnjzb3xwqqlUx2ZFLKhcBx2XhDudC9fNM9Ry")
				.get().addHeader("Content-Type", "application/json").addHeader("charset", "UTF-8").build();

		Response response = client.newCall(request).execute();

		System.out.println("헤더----------");
		System.out.println(response.headers().toString());
		System.out.println("바디 -------------");

		// System.out.println(response.body().string());

		JSONArray jsonArr = new JSONArray(response.body().string());

		// JSONObject jObj=new JSONObject();

		for (int i = 0; i < jsonArr.length(); i++) {

			System.out.println(jsonArr.getJSONObject(i).toString());

		}

		response.close();
	}

	private static RestTemplate getUnSafedRestTemplate() {

		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
		

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		
		
		CloseableHttpClient httpClient = HttpClients
										.custom()
										.setSSLSocketFactory(csf)
										.build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		
		requestFactory.setHttpClient(httpClient);
		
		return new RestTemplate(requestFactory);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			System.out.println("----------RestTemplate빌드 에러!!!!----------------------------------------");
			e.printStackTrace();
			
		}
		return null;
		
	}

	private static OkHttpClient getUnsafeOkHttpClient() {
		  try {
		    // Create a trust manager that does not validate certificate chains
		    final TrustManager[] trustAllCerts = new TrustManager[] {
		        new X509TrustManager() {
		          @Override
		          public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		          }

		          @Override
		          public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
		          }

		          @Override
		          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		            return new java.security.cert.X509Certificate[]{};
		          }
		        }
		    };

		    // Install the all-trusting trust manager
		    final SSLContext sslContext = SSLContext.getInstance("SSL");
		    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		    // Create an ssl socket factory with our all-trusting manager
		    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		    OkHttpClient.Builder builder = new OkHttpClient.Builder();
		    
		    builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
		    builder.hostnameVerifier(new HostnameVerifier() {

	@Override
	public boolean verify(String hostname, SSLSession session) {
		return true;
	}

	});

	OkHttpClient okHttpClient = builder.build();return okHttpClient;}catch(
	Exception e){throw new RuntimeException(e);
	}}

	
	
	@Test
	public void testExchange()throws Exception{
		
		
		String apiKey="qmaIwz9MKYURW4O%2BGRV3V8YTOuBlXnjzb3xwqqlUx2ZFLKhcBx2XhDudC9fNM9Ry";

		
		
		String url = "http://data.kma.go.kr/apiData/getData?type=json&dataCd=ASOS&dateCd=DAY&startDt=20180101&endDt=20180101&stnIds=108&schListCnt=10&pageIndex=1"
				+ "&apiKey="+URLDecoder.decode(apiKey, "UTF-8");

		
		
		RestTemplate rest= getUnSafedRestTemplate();
		
		MultiValueMap<String, String> headers= new LinkedMultiValueMap<>();
		headers.add("charset", "UTF-8");
		headers.add("content-type","application/json");
		HttpEntity<Object> requestEntity=new HttpEntity<Object>(headers);
		
		
		ResponseEntity<String> entity= rest.exchange(url, HttpMethod.GET,requestEntity,String.class);
		
		System.out.println(entity.getBody().toString());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	//@Test
	public void testWetherAPI_GET() throws UnsupportedEncodingException {
		String url = "http://data.kma.go.kr/apiData/getData";
		
		

		Map<String, String> apiParams = new HashMap<>();
		apiParams.put("type", "json");
		apiParams.put("dataCd", "ASOS");
		apiParams.put("dateCd", "DAY");
		apiParams.put("startDt", "20180118");
		apiParams.put("endDt", "20180119");
		apiParams.put("stnIds", "108");
		apiParams.put("schListCnt", "10");
		apiParams.put("apiKey", "qmaIwz9MKYURW4O%2BGRV3V8YTOuBlXnjzb3xwqqlUx2ZFLKhcBx2XhDudC9fNM9Ry");
		apiParams.put("pageIndex", "1");
		

		
		
		
		restTemplate=getUnSafedRestTemplate();
		
		if(restTemplate==null) {
			throw new NullPointerException();
		}
		
		ResponseEntity<JSONObject> entity = restTemplate.getForEntity(url, JSONObject.class ,apiParams);

		JSONObject jobj=entity.getBody();
		
		System.out.println(jobj.toString());

	}

}

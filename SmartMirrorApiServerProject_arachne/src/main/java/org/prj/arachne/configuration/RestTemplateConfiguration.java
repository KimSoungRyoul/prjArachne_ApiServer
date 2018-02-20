package org.prj.arachne.configuration;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;
import okhttp3.OkHttpClient;

@Configuration
@Log4j
public class RestTemplateConfiguration {

	// weatherAPI ssl 무시 안하면 302 리다이렉트 페이로 넘어가버림 
	
	@Qualifier("unsafe")
	@Bean
	public RestTemplate getUnSafedRestTemplate() {

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
			log.info("----------RestTemplate빌드 에러!!!!-------------RestTemplateConfiguration 참고---------");
			log.error("----------RestTemplate빌드 에러!!!!----------------------------------------");
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	
	
	
	
	
	

/*	@Bean
	@Qualifier("normal")
	public RestTemplate restTemplate(MappingJackson2HttpMessageConverter converter) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(converter));

		return restTemplate;

	}
*/
	@Bean
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(ObjectMapper mapper) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(mapper);
		List<MediaType> mediaTypeList = new ArrayList<>();
		Charset utf8 = Charset.forName("UTF-8");
		mediaTypeList.add(new MediaType("application", "json", utf8));
		mediaTypeList.add(new MediaType("text", "html", utf8));
		// mediaTypeList.add(new MediaType("text", "plain", utf8));
		converter.setSupportedMediaTypes(mediaTypeList);
		return converter;
	}
	
	
	@Bean
	@Qualifier("unsafe")
	public OkHttpClient getUnsafeOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}
			} };

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			OkHttpClient.Builder builder = new OkHttpClient.Builder();

			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}

			});

			OkHttpClient okHttpClient = builder.build();
			return okHttpClient;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}

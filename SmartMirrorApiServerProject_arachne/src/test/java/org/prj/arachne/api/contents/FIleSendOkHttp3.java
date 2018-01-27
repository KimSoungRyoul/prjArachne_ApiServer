package org.prj.arachne.api.contents;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class FIleSendOkHttp3 {

	public static void main(String[] args) {

		File file=new File("C:\\ArachneProject\\testMedia\\profile_image.jpg");
		
		System.out.println(file.getName());
		System.out.println(file.exists());
		
		  try {
		        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
		            .addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file))
		            .build();

		        String url = "http://localhost/api/contents/KimSoungRyoul@gmail.com/tImage";
		        okhttp3.Request request = new okhttp3.Request.Builder()
		            .url(url)
		            .header("x-auth-token", "81be917d-1af6-4324-a409-d77da15b62e0")
		            .post(requestBody)
		            .build();

		        OkHttpClient client = new OkHttpClient();
		        okhttp3.Response response = client.newCall(request).execute();
		        System.out.println(response.body().string());
		        
		        
		    } catch (UnknownHostException | UnsupportedEncodingException e) {
		        e.printStackTrace();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		
		
		
		
		
	}
}

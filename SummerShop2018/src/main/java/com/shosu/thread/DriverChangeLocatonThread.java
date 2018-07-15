package com.shosu.thread;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import com.shosu.orm.Driver ;



public class DriverChangeLocatonThread extends Thread{
	   private Thread t;
	   private Driver driver;
	   private boolean isLogin=false;
	   boolean running = true;
	   private String threadName;
	   private String domain;
	   private static int totalThread=0;
	   public DriverChangeLocatonThread( String name,Driver dri,String domain){
		   this.threadName=name;
		   this.driver = dri;
		   this.domain=domain;
		   totalThread++;

	   }
	   public void terminate() {
	        running = false;
	        System.out.println("Thread " +  driver.getDriverId() + " interrupted.");
	    }
	   public void run() {
	      System.out.println("Running " +  driver.getDriverId() );
	      Gson gson = new Gson();
	      try {
	    	  while(running) {
		    	  if(!isLogin) {
//			    		  	HashMap<String,String> requestParams=new HashMap<String , String>();
//			    		  	String value="{"+
//			    		  			"\"uid\":"+"\""+driver.getTaxiNetUsers().getUsername()+"\""
//			    		  			+",\"pw\":"+"\""+driver.getTaxiNetUsers().getPassword()+"\""
//			    		  			+",\"lan\":"+"\""+driver.getLanguageCode()+"\""
//			    		  			+",\"currentCntry\":"+"\""+driver.getCountryCode() +"\""
//			    		  			+",\"mid\":"+"\""+driver.getRegId()+"\""
//			    		  			+",\"mType\":"+"\""+driver.getDeviceType()+"\""		    		  			
//			    		  			+",\"version\":"+"\""+driver.getVersion()+"\""
//			    		  			+",\"versionName\":"+"\""+driver.getVersion()+"\""+"}"
//			    		  			;
//			    		  	requestParams.put("json", value);
//			    		  	String uri =domain+ "/TN/restServices/DriverController/Login";		    		 		
//			    			String nigga = HttpClientUtil.sendPost(uri, requestParams);
//			    	        BaseDTO basedto = gson.fromJson(nigga, BaseDTO.class);
//
//			    	        
//			    		  	System.out.println("Thread "+ driver.getDriverId() +" had login status: "  + basedto.getStatus() + " .");
//			    		  	if(basedto.getStatus().equals(ErrorCodes.SUCCESS)) {
//			    		  		isLogin=true;
//			    		  		
//			    		  	}else {
//			    		  		terminate();
//			    		  		totalThread--;
//			    		  		log.info("Thread total " +  totalThread + " interact .");
//			    		  	}
		    		  isLogin = true;
		    	  	}else {

		    	  		HashMap<String,String> requestParams=new HashMap<String , String>();
		    	  		String value="{"
		    		  			+"\"id\":"+"\""+driver.getDriverId()+"\""
		    		  			+",\"lat\":"+Math.random()*100
		    		  			+",\"long\":"+Math.random()*100+"}"		   
		    		  			;
		    	  		
		    	  		requestParams.put("json", value);
		    	  		String uri =domain+ "/TN/restServices/DriverController/UpdateCurrentLocation2";		    		 		
		    	  		String nigga = sendPost(uri, requestParams);  
		    	        
		    		  	System.out.println("Thread " +driver.getDriverId() +  "  updated location.");
		    	  		Thread.sleep(1000);
		    	  	}    	  
	    	  }		         
	     } catch (  Exception e) {
	         System.out.println("Thread " +  driver.getDriverId() + " interrupted.");
	         totalThread--;
	     }
	      	 System.out.println("Thread " +  driver.getDriverId() + " exiting.");
	   }
	   
	   public void start ()
	   {
	      System.out.println("Starting " +  driver.getDriverId() );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start();
	      }
	   }
	   
	   public static String sendPost(String url, HashMap<String, String> params) throws Exception {

			// String url = "https://selfsolve.apple.com/wcResults.do";

			// HttpClient client = HttpClientBuilder.create().build();
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			// add header
			post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

			Iterator uee = params.entrySet().iterator();

			while (uee.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) uee.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				urlParameters.add(new BasicNameValuePair(key, value));
			}

			post.setEntity(new UrlEncodedFormEntity(urlParameters));

			HttpResponse response = client.execute(post);
			InputStream in = response.getEntity().getContent();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int read = 0;
			while ((read = in.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			os.flush();
			byte[] myByte = os.toByteArray();
			Header[] headers = response.getHeaders("Content-Type");
			String encode = "UTF-8";
			for (int i = 0; i < headers.length; i++) {
				Header header = headers[i];
				String[] b = header.getValue().split("\\=", 2);
				if(b.length>1) {
				encode = b[1];
				}
			}
			String result = new String(myByte, encode);
			// response.getEntity().getContent();
			// logger.debug(result.toString());
			os.close();
			return result;
	   }
	   
}

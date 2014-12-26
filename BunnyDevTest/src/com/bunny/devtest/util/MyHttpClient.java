package com.bunny.devtest.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author AndresPachon
 * Simple utility class for doing http get and post requests.
 */
public class MyHttpClient {

	public static String fetch(String url, String optionalUserAgent, String optionalHeaderName, String optionalHeaderValue){
		StringBuilder sb = null;
		URLConnection conn = null;
		BufferedReader reader = null;
		InputStream is = null;
		String s = null;

		try {
			sb = new StringBuilder();
			conn = new URL(url).openConnection();
			if(optionalUserAgent!=null)
				conn.setRequestProperty("User-Agent", optionalUserAgent);
			if(optionalHeaderName!=null && optionalHeaderValue!=null)
				conn.setRequestProperty(optionalHeaderName, optionalHeaderValue);
			
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.connect();
			is = new BufferedInputStream(conn.getInputStream());
			reader = new BufferedReader(new InputStreamReader(is));
			
			String temp;
			while( (temp = reader.readLine()) != null)
				sb.append(temp);
			s = sb.toString();
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null) 
					reader.close();
				if(is!=null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}
	
	public static String post(String url, Map<String, String> params){
		
		String s = null;
		StringBuilder sb = null;
		
		try{
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			Set<Entry<String, String>> set = params.entrySet();
			for(Entry<String, String> entry:set){
				nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    HttpResponse response = client.execute(post);
		    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    
		    String temp;
		    sb = new StringBuilder();
			while( (temp = rd.readLine()) != null)
				sb.append(temp);
			s = sb.toString();
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return s;
	}
}

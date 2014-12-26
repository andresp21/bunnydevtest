package com.bunny.devtest.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.bunny.devtest.util.MyHttpClient;

/**
 * @author AndresPachon
 * Simple class for making API calls to the Voicebunny API
 */
public class VoiceBunnyService {
	
	private static final Log LOG = LogFactory.getLog(VoiceBunnyService.class);
	private static String VOICEBUNNY_API_USER = "41130";
	private static String VOICEBUNNY_API_TOKEN = "cbbd4112930a52d247e69fc9d263509d";
	private static String VOICEBUNNY_API_ENDPOINT = "https://"+VOICEBUNNY_API_USER+":"+VOICEBUNNY_API_TOKEN+"@api.voicebunny.com/projects/addSpeedy"; 
	
	public static String createSpeedyProject(String script, boolean test){

		String projectId = null;
		try{
			
			//1) Make API call
			Map<String, String> params = new HashMap<String, String>();
	    	params.put("test", test?"1":"0");
	    	params.put("script", script);
	        params.put("title", "AndresPachon's dev test!");
	        params.put("language", "eng-us");
	        params.put("genderAndAge", "middleAgeMale");
	        params.put("lifetime", "86400");
	        params.put("remarks", "Try to sound super cool, I really want to join Bunny Inc.!");
	        params.put("syncedRecording", "0");
	        params.put("ping", "andresp21@gmail.com");
	        //params.put("price", "1.00");
			String jsonResponse = MyHttpClient.post(VOICEBUNNY_API_ENDPOINT, params);
			LOG.info("VoiceBunny server response:"+jsonResponse);
			
			//2) Parse response and return project id
			JSONObject mainJson = new JSONObject(jsonResponse);
			if(mainJson.has("project"))
				projectId = mainJson.getJSONObject("project").getString("id");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return projectId;
	}
}

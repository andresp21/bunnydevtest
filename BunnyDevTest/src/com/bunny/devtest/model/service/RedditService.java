package com.bunny.devtest.model.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.bunny.devtest.util.MyHttpClient;
import com.bunny.model.bo.TrendyArticle;

/**
 * @author AndresPachon
 * Simple class that retrieves the title of the trendiest article on Reddit.  
 */
public class RedditService {
	
	private static final String REDDIT_API_ENDPOINT = "http://www.reddit.com/.json";
	
	public static TrendyArticle getTrendyArticle(){
		
		TrendyArticle article = new TrendyArticle();
		
		try {
			
			//1) Make call to Reddit's API
			String jsonFromReddit = MyHttpClient.fetch(REDDIT_API_ENDPOINT, null, null, null);
			if(jsonFromReddit!=null){
				
				//2) Parse JSON, extract title from most popular article.
				JSONObject mainJsonObject = new JSONObject(jsonFromReddit);
				article.setTitle(((((JSONObject) mainJsonObject.getJSONObject("data")).getJSONArray("children")).getJSONObject(0)).getJSONObject("data").getString("title"));
				article.setLink(((((JSONObject) mainJsonObject.getJSONObject("data")).getJSONArray("children")).getJSONObject(0)).getJSONObject("data").getString("url"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return article;
	}
}

package com.bunny.devtest.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bunny.devtest.model.service.RedditService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author AndresPachon
 * Main entry point to the web app. It pulls the trendiest article on reddit
 * and shows it right away to the user.
 */
public class MainAction extends ActionSupport {
	
	private static final Log LOG = LogFactory.getLog(MainAction.class);
	private String trendiestArticleTitle;
	
	public String getTrendiestArticleTitle() {
		return trendiestArticleTitle;
	}
	
	public void setTrendiestArticleTitle(String trendiestArticleTitle) {
		this.trendiestArticleTitle = trendiestArticleTitle;
	}

	public String execute(){
		
		LOG.info("Pulling trendiest article from Reddit...");
		trendiestArticleTitle = RedditService.getTrendyArticle();
		
		LOG.info("All set! Trendiest article right now is:"+trendiestArticleTitle);
		return SUCCESS;
	}
}

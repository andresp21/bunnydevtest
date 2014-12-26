package com.bunny.devtest.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bunny.devtest.model.service.RedditService;
import com.bunny.model.bo.TrendyArticle;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author AndresPachon
 * Main entry point to the web app. It pulls the trendiest article on reddit
 * and shows it right away to the user.
 */
public class MainAction extends ActionSupport {
	
	private static final Log LOG = LogFactory.getLog(MainAction.class);
	private String trendiestArticleTitle;
	private String trendiestArticleLink;
	
	public String getTrendiestArticleTitle() {
		return trendiestArticleTitle;
	}
	
	public void setTrendiestArticleTitle(String trendiestArticleTitle) {
		this.trendiestArticleTitle = trendiestArticleTitle;
	}
	
	public String getTrendiestArticleLink() {
		return trendiestArticleLink;
	}

	public void setTrendiestArticleLink(String trendiestArticleLink) {
		this.trendiestArticleLink = trendiestArticleLink;
	}

	public String execute(){
		
		LOG.info("Pulling trendiest article from Reddit...");
		TrendyArticle a = RedditService.getTrendyArticle();
		trendiestArticleTitle = a.getTitle();
		trendiestArticleLink = a.getLink();
		
		LOG.info("All set! Trendiest article right now is:"+trendiestArticleTitle+" | Link:"+trendiestArticleLink);
		return SUCCESS;
	}
}

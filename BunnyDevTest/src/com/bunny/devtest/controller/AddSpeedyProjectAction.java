package com.bunny.devtest.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bunny.devtest.model.service.VoiceBunnyService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author AndresPachon
 * Simple class that makes a call to Voicebunny's API in order to 
 * create a new speedy project with the article info pulled from Reddit.
 */
public class AddSpeedyProjectAction extends ActionSupport {
	
	private static final Log LOG = LogFactory.getLog(AddSpeedyProjectAction.class);
	private String projectInfo;
	private String trendiestArticleTitle;
	
	public String getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}
	public String getTrendiestArticleTitle() {
		return trendiestArticleTitle;
	}
	public void setTrendiestArticleTitle(String trendiestArticleTitle) {
		this.trendiestArticleTitle = trendiestArticleTitle;
	}
	
	public String execute() {
		try {
			LOG.info("Creating new speedy project for article with Title: "+trendiestArticleTitle);
			projectInfo = VoiceBunnyService.createSpeedyProject(trendiestArticleTitle, true);
			
			LOG.info("All set! Project created! Details: "+projectInfo);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}

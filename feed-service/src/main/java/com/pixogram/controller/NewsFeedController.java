package com.pixogram.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.common.RestServiceTemplateUtils;
import com.pixogram.entity.NewsFeed;
import com.pixogram.service.NewsFeedService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/newsfeed")
public class NewsFeedController {
	
	private static final String NEWSFEED = "newsfeed"; 

	private static final String TO = " ) to : ( ";
	
	@Autowired
	private NewsFeedService newsFeedService;
	
	@PostMapping("/saveNewsFeed/{username}")
	public Map<String, Object> saveNewsFeed(@PathVariable("username") final String username,@RequestParam("description") final String feed,
			@RequestParam("files") final List<MultipartFile> files,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(NEWSFEED, newsFeedService.saveNewsFeed(feed,files,username));
		if (log.isInfoEnabled()) {
			log.info("Rest url /saveNewsFeed from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	@GetMapping("/getLatestNewsFeed")
	public Map<String, Object> getLatestNewsFeed(@RequestParam() final String username,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(NEWSFEED, newsFeedService.getLatestNewsFeed(username));
		if (log.isInfoEnabled()) {
			log.info("Rest url /getLatestNewsFeed from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	@GetMapping("/getMyNewsFeed/{username}")
	public Map<String, Object> getMyNewsFeed(@PathVariable("username") final String username,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(NEWSFEED, newsFeedService.getMyNewsFeed(username));
		if (log.isInfoEnabled()) {
			log.info("Rest url /getMyNewsFeedgetMyNewsFeed from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	@GetMapping("/deleteNewsFeed")
	public Map<String, Object> deleteNewsFeed(@RequestParam("id") final long id,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("newsfeed", this.newsFeedService.deleteNewsFeed(id));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	
	
	

}

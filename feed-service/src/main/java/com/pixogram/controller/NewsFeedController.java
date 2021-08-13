package com.pixogram.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.common.CustomException;
import com.pixogram.common.RestServiceTemplateUtils;
import com.pixogram.entity.NewsFeed;
import com.pixogram.service.NewsFeedService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/newsfeed")
public class NewsFeedController {
	
	private static final String NEWSFEED = "newsfeed"; 

	private static final String TO = " ) to : ( ";
	
	@Autowired
	private NewsFeedService newsFeedService;
	
	@PostMapping("/saveNewsFeed")
	public Map<String, Object> saveNewsFeed(@RequestBody() final NewsFeed feed,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(NEWSFEED, newsFeedService.saveNewsFeed(feed));
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
	
	@GetMapping("/getMyNewsFeed")
	public Map<String, Object> getMyNewsFeed(@RequestParam() final String username,
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
		map.put("userList", this.newsFeedService.deleteNewsFeed(id));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	
	
	

}

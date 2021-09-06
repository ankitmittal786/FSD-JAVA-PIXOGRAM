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
import com.pixogram.service.FeedActivityService;
import com.pixogram.service.NewsFeedService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/activity")
public class FeedActivityController {
	
	private static final String ACTIVITY = "activity"; 

	private static final String TO = " ) to : ( ";
	
	@Autowired
	private FeedActivityService feedActivityService;
	
	
	@GetMapping("/saveLike")
	public Map<String, Object> saveLike(@RequestParam("username") final String username,
			@RequestParam("uri") final String uri,
			@RequestParam("feedId") final long feedId,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(ACTIVITY, feedActivityService.saveLike(feedId,username,uri));
		if (log.isInfoEnabled()) {
			log.info("Rest url /saveNewsFeed from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	@GetMapping("/deleteLike")
	public Map<String, Object> deleteLike(@RequestParam("username") final String username,
			@RequestParam("feedId") final long feedId,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(ACTIVITY, feedActivityService.deleteLike(feedId,username));
		if (log.isInfoEnabled()) {
			log.info("Rest url /saveNewsFeed from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	@GetMapping("/saveComment")
	public Map<String, Object> saveComment(@RequestParam("username") final String username,
			@RequestParam("feedId") final long feedId,
			@RequestParam("comment") final String comment,
			@RequestParam("uri") final String uri,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(ACTIVITY, feedActivityService.saveComment(feedId,username,comment,uri));
		if (log.isInfoEnabled()) {
			log.info("Rest url /saveNewsFeed from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	@GetMapping("/deleteComment")
	public Map<String, Object> deleteComment(@RequestParam("username") final String username,
			@RequestParam("feedId") final long feedId,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(ACTIVITY, feedActivityService.deleteComment(feedId,username));
		if (log.isInfoEnabled()) {
			log.info("Rest url /saveNewsFeed from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	
	
	
	
	

}

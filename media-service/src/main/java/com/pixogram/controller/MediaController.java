package com.pixogram.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.common.RestServiceTemplateUtils;
import com.pixogram.entity.Media;
import com.pixogram.service.MediaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/media")
public class MediaController {
	
	private static final String MEDIA = "media";

	private static final String TO = " ) to : ( ";
	
	@Autowired
	private MediaService mediaService;
	
	@PostMapping("uploadMedia")
	public Map<String, Object> saveGallary(@RequestParam("file") final List<MultipartFile> files,
			@RequestParam("username") final String username,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Media> map = new HashMap<>();
		map.put(MEDIA, mediaService.uploadMedia(files,username));
		if (log.isInfoEnabled()) {
			log.info("Rest url /saveGallary from : ( " + startDate + TO + new Date() + ")");
		}
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}

}

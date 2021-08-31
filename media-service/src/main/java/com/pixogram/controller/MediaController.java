package com.pixogram.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.common.RestServiceTemplateUtils;
import com.pixogram.service.S3Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/media")
public class MediaController {
	
	private static final String MEDIA = "media";

	private static final String TO = " ) to : ( ";
	
	@Autowired
	private S3Service s3Service;
	
	@PostMapping("uploadMedia/{username}/{id}")
	public Map<String, String> uploadMedia(@RequestParam final List<MultipartFile> files,
			@PathVariable("username") final String username,@PathVariable("id") final long id,
			final HttpServletResponse response) throws CustomException {
		final Date startDate = new Date();
		Map<String, String> map = new HashMap<>();
		map= s3Service.uploadFiles(files,username,id);
		if (log.isInfoEnabled()) {
			log.info("Rest url /uploadMedia from : ( " + startDate + TO + new Date() + ")");
		}
		return map;
	}
	
	@PostMapping("/uploadprofile/{username}")
	public String uploadprofile(@RequestParam("file") final MultipartFile file,
			@PathVariable final String username, final HttpServletResponse response) throws CustomException {
		log.info("Rest url /uploadprofile from :  "+ new Date() );
		return this.s3Service.uploadFile(username+"-"+new Date().getTime()+"-"+file.getName(),file);
	}
	
	@PostMapping("/deleteimage")
	public boolean deleteimage(@RequestParam("uri") final String uri, final HttpServletResponse response) throws CustomException {
		log.info("Rest url /uploadprofile from :  "+ new Date() );
		return this.s3Service.deleteimage(uri);
	}

}

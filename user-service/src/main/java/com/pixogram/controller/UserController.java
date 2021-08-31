package com.pixogram.controller;

import java.security.Principal;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.common.RestServiceTemplateUtils;
import com.pixogram.entity.User;
import com.pixogram.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("user")
public class UserController {
	
	private static final String USER = "user";

	private static final String TO = " ) to : ( ";
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUser")
	public ResponseEntity<User> user(Principal principal) {
		final Date startDate = new Date();
		User user = userService.getLoggedInUser(principal);
		log.info("Rest url /getUser hitting time: from ( " + startDate + " ) to : ( " + new Date() + ")");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("/register/{id}")
	public Map<String, Object> saveUser(@RequestBody @Valid final User user, final Errors errors,
			@PathVariable final long id, final HttpServletResponse response) throws CustomException {
		final List<String> errorList = RestServiceTemplateUtils.errorAfterSkipNull(errors);
		if (!errorList.isEmpty()) {
			return RestServiceTemplateUtils.errorResponse(errorList, response);
		}
		final Map<String, Object> map = new HashMap<>();
		map.put(USER, this.userService.saveUser(user, id));
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	
	@GetMapping("/{username}")
	public Map<String, Object> getUserWithId(@PathVariable("username") final String username, final HttpServletResponse response)
			throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put(USER, this.userService.findByUsername(username));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	
	@PutMapping("/changePassword")
	public ResponseEntity<Map<String, Object>> changePassword(@RequestParam final String user,
			@RequestParam final String old, @RequestParam final String current) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(USER, this.userService.changePassword(user, new String(Base64.getDecoder().decode(old)),
				new String(Base64.getDecoder().decode(current))));
		if (log.isInfoEnabled()) {
			log.info("Rest url /changePassword from : ( " + startDate + TO + new Date() + ")");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PutMapping("/resetPassword")
	public ResponseEntity<Map<String, Object>> resetPassword(@RequestParam final long id,
			@RequestParam final String current, @RequestParam final long organizationId) throws CustomException {
		final Date startDate = new Date();
		final Map<String, Object> map = new HashMap<>();
		map.put(USER,
				this.userService.resetPassword(id, new String(Base64.getDecoder().decode(current)), organizationId));
		if (log.isInfoEnabled()) {
			log.info("Rest url /resetPassword from : ( " + startDate + TO + new Date() + ")");
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/check/{username}")
	public Map<String, Object> checkUserWithUsername(@PathVariable("username") final String username, final HttpServletResponse response)
			throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put(USER, this.userService.checkUserWithUsername(username));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@PostMapping("/uploadprofile/{username}")
	public Map<String, Object> uploadprofile(@RequestParam("file") final MultipartFile file,
			@PathVariable final String username, final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put(USER, this.userService.uploadProfile(file, username));
		return RestServiceTemplateUtils.createdSuccessResponse(map, response);
	}
	
	@GetMapping("/getUserList")
	public Map<String, Object> getUserList( final HttpServletResponse response)
			throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put(USER, this.userService.getUserList());
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	
	
	
	

}

package com.pixogram.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.common.CustomException;
import com.pixogram.common.RestServiceTemplateUtils;
import com.pixogram.service.AccountService;
import com.pixogram.service.AccountServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("account")
public class AccountController {
	
	
	private AccountService accountService;	
	
	@Autowired
	AccountController(){
		accountService=new AccountServiceImpl();
	}
	
	@GetMapping("/getFollowingUserList")
	public Map<String, Object> getFollowingUserList(@RequestParam("userId") final String userId,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.getFollowingUserList(userId));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@GetMapping("/getFollowersUserList")
	public Map<String, Object> getFollowersUserList(@RequestParam("userId") final String userId,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.getFollowersUserList(userId));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@GetMapping("/getBlockedUserList")
	public Map<String, Object> getBlockedUserList(@RequestParam("userId") final String userId,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.getBlockedUserList(userId));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@GetMapping("/blockUser")
	public Map<String, Object> blockUser(@RequestParam("username") final String username, @RequestParam("name") final String name, @RequestParam("usernameuri") final String usernameuri,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.blockUser(username,name,usernameuri));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@GetMapping("/unblockUser")
	public Map<String, Object> unblockUser(@RequestParam("id") final long id,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.unblockUser(id));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@GetMapping("/followUser")
	public Map<String, Object> followUser(@RequestParam("username") final String username, @RequestParam("name") final String name, @RequestParam("usernameuri") final String usernameuri, @RequestParam("uri") final String uri,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.followUser(username,name,usernameuri,uri));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@GetMapping("/unfollowUser")
	public Map<String, Object> unfollowUser(@RequestParam("id") final long id,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.unfollowUser(id));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	@GetMapping("/getSuggestedUserBasedOnMutual")
	public Map<String, Object> getSuggestedUserBasedOnMutual(@RequestParam("username") final String username,
			final HttpServletResponse response) throws CustomException {
		final Map<String, Object> map = new HashMap<>();
		map.put("userList", this.accountService.getSuggestedUserBasedOnMutual(username));
		return RestServiceTemplateUtils.getRecordSuccessResponse(map, response);
	}
	
	
	
	

}

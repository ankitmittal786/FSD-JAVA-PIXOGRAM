package com.pixogram.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.entity.User;

@Component
@FeignClient(name = "user-service")
public interface UserClient {

	@RequestMapping(method = RequestMethod.GET,value = "/user-service/user/getUserList")
	public List<User> getUserList();
}


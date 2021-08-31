package com.pixogram.service;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.entity.User;

@Service
public interface UserService {

	User saveUser(@Valid User user, long id) throws CustomException;

	User findById(long id);

	boolean resetPassword(long id, String string, long organizationId) throws CustomException;

//	int changeStatusOfUser(long id, boolean status);

	User findByUsername(String username);

	

	boolean changePassword(String username, String old, String current) throws CustomException;

	void createDefaultUser();

	boolean checkUserWithUsername(String username);

	User getLoggedInUser(Principal principal);

	Object uploadProfile(MultipartFile file, String username);

	List<User> getUserList();

}

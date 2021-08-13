package com.pixogram.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.pixogram.common.CustomException;
import com.pixogram.entity.User;

@Service
public interface UserService {

	User saveUser(@Valid User user, long id);

	User findById(long id);

	boolean resetPassword(long id, String string, long organizationId) throws CustomException;

	

	int changeStatusOfUser(long id, boolean status);

	User findByUsername(String username);

	

	boolean changePassword(String username, String old, String current) throws CustomException;

}

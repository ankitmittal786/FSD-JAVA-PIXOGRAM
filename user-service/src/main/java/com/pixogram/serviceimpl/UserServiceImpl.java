package com.pixogram.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.pixogram.common.CustomException;
import com.pixogram.entity.BlockedUser;
import com.pixogram.entity.FollowerUser;
import com.pixogram.entity.User;
import com.pixogram.repository.BlockedUserRepository;
import com.pixogram.repository.FollowerUserRepository;
import com.pixogram.repository.UserRepository;
import com.pixogram.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(final User user, final long id) {
		if (user.getId() == 0) {
			final User userWithNameOrCode = this.userRepository.findByUsername(user.getUsername());
			if (userWithNameOrCode != null) {
				throw new CustomException("User with same username already exists");
			} else {
				final String password = randomAlphaNumeric(6);
				final User decodedUser = new User();
				BeanUtils.copyProperties(user, decodedUser, CommonUtils.getNullPropertyNames(user));
				user.setPassword(this.passwordEncoder.encode(password));
				final User savedUser = this.userRepository.save(user);
				/** Mail --> this.asyncService.sendMailToNewUser(user); **/
				decodedUser.setPassword(password);
//				this.createUserPermissionGroup(savedUser, user.getOrganization());
				return decodedUser;
			}

		} else {
			final User oldUser = this.userRepository.findById(id);
			oldUser.setPassword(this.passwordEncoder.encode(user.getPassword()));// for encoding password
//			oldUser.setRoles(user.getRoles());
			return this.userRepository.save(oldUser);
		}
	}


	@Override
	public User findById(final long id) {
		return this.userRepository.findById(id);
	}


	@Override
	public boolean changePassword(final String username, final String old, final String current)
			throws CustomException {
		final User user = this.userRepository.findUser(username);
		if (user != null) {
			final Boolean match = this.passwordEncoder.matches(old, user.getPassword());
			if (match) {

				final Boolean match1 = this.passwordEncoder.matches(current, user.getPassword());
				if (match1) {
					throw new CustomException("New password entered already exists.");
				} else {
					user.setPassword(this.passwordEncoder.encode(current));
					// first time user value settin
					this.userRepository.save(user);
					// mail trigger for password change
//					this.masterAsyncService.sendChangePasswordConfirmationMail(user);
					return true;
				}
			} else {
				throw new CustomException("The current password entered does not match.");
			}
		}
		throw new CustomException("User does not exist.");
	}

	@Override
	public User findByUsername(final String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public boolean resetPassword(final long id, final String current, final long organizationId)
			throws CustomException {
		User user = this.userRepository.findById(id);
		if (user != null) {

			final Boolean match = this.passwordEncoder.matches(current, user.getPassword());
			if (match) {
				throw new CustomException("New password entered already exists.");
			} else {
				user.setPassword(this.passwordEncoder.encode(current));
				user = this.userRepository.save(user);
//				this.masterAsyncService.sendMailToUserOnPasswordResetManually(user, current);
				return true;
			}
		}
		throw new CustomException("User does not exist.");
	}


	@Override
	public int changeStatusOfUser(final long id, final boolean status) {
		return this.userRepository.changeStatusOfUser(id, status);
	}

	
	






	

	

	

	

}

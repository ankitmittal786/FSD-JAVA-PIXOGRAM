package com.pixogram.serviceimpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.client.MediaClient;
import com.pixogram.common.CommonUtils;
import com.pixogram.common.CustomException;
import com.pixogram.entity.User;
import com.pixogram.repository.UserRepository;
import com.pixogram.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MediaClient mediaClient;
	
	@Autowired
	public UserServiceImpl() {
		passwordEncoder=new BCryptPasswordEncoder();
	}
	
	

	@Override
	public User saveUser(final User user, final long id) throws CustomException {
		if (user.getId() == 0) {
			final User userWithNameOrCode = this.userRepository.findByUsername(user.getUsername());
			if (userWithNameOrCode != null) {
				throw new CustomException("User with same username already exists");
			} else {
				final String password = randomAlphaNumeric(6);
				final User decodedUser = new User();
				BeanUtils.copyProperties(user, decodedUser, CommonUtils.getNullPropertyNames(user));
				user.setPassword(this.passwordEncoder.encode(password));
				user.setCreatedDateTime(new Date());
				user.setStatus(true);
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
		final User user = this.userRepository.findUserByUsername(username);
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


//	@Override
//	public int changeStatusOfUser(final long id, final boolean status) {
//		return this.userRepository.changeStatusOfUser(id, status);
//	}
	
	private static String randomAlphaNumeric(int count) {
		final String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
		final StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			final int character = new Random().nextInt(alphaNumericString.length() - 1);
			builder.append(alphaNumericString.charAt(character));
		}
		return builder.toString();
	}


	@Override
	public void createDefaultUser() {
			if (userRepository.count() == 0) {
				User user = new User(1,"ADMIN","ADMIN","ADMIN",passwordEncoder.encode("admin@123"), "ADMIN","admin@pixogram.com","male",null,true,"USER",null,new Date(),0,0);
				System.out.println("User : "+user);
				userRepository.save(user);
			}
		}



	@Override
	public boolean checkUserWithUsername(String username) {
		return userRepository.findByUsername(username)!=null?true:false;
	}



	@Override
	public User getLoggedInUser(Principal principal) {
		if (principal != null && !principal.getName().isEmpty()) {
			User user = userRepository.findByUsernameAndStatusTrue(principal.getName());
			System.out.println("Authorities : "+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			return user;
		}
		throw new UsernameNotFoundException("The user doesn't exist");
	}



	@Override
	public String uploadProfile(MultipartFile file, String username) {
		User user = this.userRepository.findByUsername(username);
		if (user != null) {
			String uri=user.getProfilePictureURL();
			if(uri!=null) {
				mediaClient.deleteImage(uri);
			}
			uri=mediaClient.uploadProfile(file,username);
			user.setProfilePictureURL(uri);
			userRepository.save(user);
			return uri;
		}
		return null;
	}



	@Override
	public List<User> getUserList() {
		return userRepository.findAll();
	}
	
	
	
	

}

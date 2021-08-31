package com.pixogram.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pixogram.common.CustomException;
import com.pixogram.entity.User;
import com.pixogram.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
		User user = userRepository.findByUsernameOrEmailAndStatusTrue(username,username);
		if (user != null) {
//			List<String> permisssionList = userPermissionRepository.getPermissionListForUsername(username);
			List<GrantedAuthority> authorities = new ArrayList<>();
//			for (String permission : permisssionList) {
//				authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + permission));
//			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					authorities);
		} else {
			try {
				throw new CustomException(String.format("The username %s doesn't exist", username));
			} catch (CustomException e) {
				log.error(e.getMessage());
			}
		}
		return null;
	}

}

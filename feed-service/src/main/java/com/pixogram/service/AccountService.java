package com.pixogram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pixogram.entity.BlockedUser;
import com.pixogram.entity.FollowerUser;


@Service
public interface AccountService {

	List<BlockedUser> getBlockedUserList(String username);

	List<FollowerUser> getFollowingUserList(String username);

	List<FollowerUser> getFollowersUserList(String username);

	boolean blockUser(String username, String name, String usernameuri);
	
	boolean followUser(String username, String name, String usernameuri,String nameuri);

	boolean unblockUser(long id);

	boolean unfollowUser(long id);

	List<String> getFollowingUserName(String username);

	List<FollowerUser> getSuggestedUserBasedOnMutual(String username);

}

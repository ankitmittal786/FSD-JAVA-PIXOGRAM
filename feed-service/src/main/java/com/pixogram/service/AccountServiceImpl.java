package com.pixogram.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.client.UserClient;
import com.pixogram.entity.BlockedUser;
import com.pixogram.entity.FollowerUser;
import com.pixogram.entity.User;
import com.pixogram.repository.BlockedUserRepository;
import com.pixogram.repository.FollowerRepository;



@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private FollowerRepository fuRepository;
	
	@Autowired
	private BlockedUserRepository blockedRepository;
	
	@Autowired
	private UserClient userClient;
	
	

	@Override
	public List<FollowerUser> getFollowersUserList(final String username) {
		return this.fuRepository.findByFollowingUsername(username);
		
	}

	@Override
	public List<BlockedUser> getBlockedUserList(final String username) {
		return this.blockedRepository.findByUsername(username);
	}

	@Override
	public List<FollowerUser> getFollowingUserList(final String username) {
		return this.fuRepository.findByUsername(username);
	}


	@Override
	public boolean blockUser(String username, String name,String usernameuri ) {
		BlockedUser blockedUser=new BlockedUser(username,name,usernameuri);
		return blockedRepository.save(blockedUser) != null;
	}


	@Override
	public boolean followUser(String username, String name,String usernameuri,String uri) {
		FollowerUser fu=new FollowerUser(username,name,usernameuri,uri);
		return fuRepository.save(fu) != null;
	}


	@Override
	public boolean unblockUser(long id) {
		 blockedRepository.deleteById(id);
		 return true;
	}


	@Override
	public boolean unfollowUser(long id) {
		// TODO Auto-generated method stub
		fuRepository.deleteById(id);
		return true;
	}

	@Override
	public List<String> getFollowingUserName(final String username) {
		return this.fuRepository.findFollowingUsernameByUsername(username);
	}

	@Override
	public List<FollowerUser> getSuggestedUserBasedOnMutual(String username) {
		
		List<String> followingList=fuRepository.findDistinctFollowingUsernameByUsername(username);
		if(followingList.isEmpty()) {
			List<User> users=userClient.getUserList();
			return users.parallelStream().map(user->new FollowerUser(user)).collect(Collectors.toList());
			
		}
//		List<String> usernames=followingList.stream().map(data->data.getFollowingUsername()).collect(Collectors.toList());
		return this.fuRepository.findByFollowingUsernameIn(followingList);
	}

}

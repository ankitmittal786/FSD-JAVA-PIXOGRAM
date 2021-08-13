package com.pixogram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.entity.BlockedUser;
import com.pixogram.entity.FollowerUser;
import com.pixogram.repository.BlockedUserRepository;
import com.pixogram.repository.FollowerUserRepository;



@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BlockedUserRepository blockedRepository;
	
	@Autowired
	private FollowerUserRepository followerRepository;
	

	@Override
	public List<FollowerUser> getFollowersUserList(final String username) {
		return this.followerRepository.findByFollowingUsername(username);
		
	}

	@Override
	public List<BlockedUser> getBlockedUserList(final String username) {
		return this.blockedRepository.findByUsername(username);
	}

	@Override
	public List<FollowerUser> getFollowingUserList(final String username) {
		return this.followerRepository.findByUsername(username);
	}


	@Override
	public boolean blockUser(String username, String name,String usernameuri ) {
		BlockedUser blockedUser=new BlockedUser(username,name,usernameuri);
		return blockedRepository.save(blockedUser) != null;
	}


	@Override
	public boolean followUser(String username, String name,String usernameuri,String uri) {
		FollowerUser fu=new FollowerUser(username,name,usernameuri,uri);
		return followerRepository.save(fu) != null;
	}


	@Override
	public boolean unblockUser(long id) {
		 blockedRepository.deleteById(id);
		 return true;
	}


	@Override
	public boolean unfollowUser(long id) {
		// TODO Auto-generated method stub
		 followerRepository.deleteById(id);
		return true;
	}

	@Override
	public List<String> getFollowingUserName(final String username) {
		return this.followerRepository.findFollowingUserNameByUsername(username);
	}

	@Override
	public List<FollowerUser> getSuggestedUserBasedOnMutual(String username) {
		List<String> followingList=this.followerRepository.findFollowingUserNameByUsername(username);
		return this.followerRepository.findByFollowingUsernameIn(followingList);
	}

}
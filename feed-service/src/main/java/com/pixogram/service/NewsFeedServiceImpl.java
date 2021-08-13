package com.pixogram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.entity.NewsFeed;
import com.pixogram.repository.NewsFeedRepository;

@Service
public class NewsFeedServiceImpl implements NewsFeedService{
	
	@Autowired
	private NewsFeedRepository repository;
	
	@Autowired
	private AccountService accountService;
	

	@Override
	public NewsFeed saveNewsFeed(NewsFeed feed) {
		return repository.save(feed);
	}


	@Override
	public List<NewsFeed> getLatestNewsFeed(String username) {
		List<String> usernames=accountService.getFollowingUserName(username);
		return repository.findByUsernameInOrderBypostedDateTimeDesc(usernames);
	}


	@Override
	public Object deleteNewsFeed(long id) {
		repository.deleteNewsFeedById(id);
		return true;
	}
	
	
	@Override
	public List<NewsFeed> getMyNewsFeed(String username) {
		return repository.findByUsernameInOrderBypostedDateTimeDesc(username);
	}
	
	
	
	
	
}

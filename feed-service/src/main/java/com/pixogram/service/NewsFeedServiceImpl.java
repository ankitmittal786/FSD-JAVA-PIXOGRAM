package com.pixogram.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.client.MediaClient;
import com.pixogram.common.CustomException;
import com.pixogram.entity.Media;
import com.pixogram.entity.NewsFeed;
import com.pixogram.repository.NewsFeedRepository;

@Service
public class NewsFeedServiceImpl implements NewsFeedService{
	
	@Autowired
	private NewsFeedRepository repository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MediaService mediaService;
	
	

	@Override
	public NewsFeed saveNewsFeed(String description,List<MultipartFile> files, String username, String uri) throws CustomException {
		NewsFeed feed=new NewsFeed();
		feed.setDescription(description);
		feed.setUsername(username);
		feed.setPostedDate(new Date());
		feed.setUsernameUri(uri);
		feed=repository.save(feed);
		List<Media> media=mediaService.uploadMedia(files, username,feed);
		System.out.println("Media---"+media.size());
		feed.setMedia(media);
		return repository.save(feed);
	}


	@Override
	public List<NewsFeed> getLatestNewsFeed(String username) {
		List<String> usernames=accountService.getFollowingUserName(username);
		Set<String> set=new HashSet<>(usernames);
		set.add(username);
		return repository.findByUsernameInOrderBypostedDateDesc(set);
	}


	@Override
	public Object deleteNewsFeed(long id) {
		repository.deleteNewsFeedById(id);
		return true;
	}
	
	
	@Override
	public List<NewsFeed> getMyNewsFeed(String username) {
		return repository.findByUsernameOrderBypostedDateDesc(username);
	}
	
	
	
	
	
}

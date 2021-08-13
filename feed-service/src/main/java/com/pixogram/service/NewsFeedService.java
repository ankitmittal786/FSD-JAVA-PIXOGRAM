package com.pixogram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pixogram.entity.NewsFeed;

@Service
public interface NewsFeedService  {

	NewsFeed saveNewsFeed(NewsFeed feed);

	List<NewsFeed> getLatestNewsFeed(String username);

	Object deleteNewsFeed(long id);

	List<NewsFeed> getMyNewsFeed(String username);

}

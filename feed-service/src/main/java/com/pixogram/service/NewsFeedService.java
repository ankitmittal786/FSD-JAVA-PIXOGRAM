package com.pixogram.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.common.CustomException;
import com.pixogram.entity.NewsFeed;

@Service
public interface NewsFeedService  {

	NewsFeed saveNewsFeed(String feed,List<MultipartFile> files, String username, String uri) throws CustomException;

	List<NewsFeed> getLatestNewsFeed(String username);

	Object deleteNewsFeed(long id);

	List<NewsFeed> getMyNewsFeed(String username);

}

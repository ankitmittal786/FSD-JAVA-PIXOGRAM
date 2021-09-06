package com.pixogram.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.entity.Comment;
import com.pixogram.entity.FeedActivity;
import com.pixogram.entity.NewsFeed;
import com.pixogram.repository.CommentRepository;
import com.pixogram.repository.FeedActivityRepository;
import com.pixogram.repository.NewsFeedRepository;

@Service
public class FeedActivityServiceImpl implements FeedActivityService {
	
	@Autowired
	private FeedActivityRepository activityRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private NewsFeedRepository feedRepository;

	@Override
	public FeedActivity saveLike(long feedId, String username, String uri) {
		NewsFeed nf=feedRepository.findNewsFeedById(feedId);
		if(nf!=null) {
			FeedActivity fa=new FeedActivity();
			fa.setDateTime(new Date());
			fa.setUsername(username);
			fa.setUsernameURI(uri);
			fa= activityRepository.save(fa);
			return fa;
		}
		return null;
	}

	@Override
	public Boolean deleteLike(long feedId, String username) {
		activityRepository.deleteByUsernameAndNewsfeed(username,new NewsFeed(feedId));
		return true;
	}

	@Override
	public Comment saveComment(long feedId, String username, String comment, String uri) {
		NewsFeed nf=feedRepository.findNewsFeedById(feedId);
		if(nf!=null) {
			Comment c=new Comment();
			c.setNewsfeed(nf);
			c.setReply(comment);
			c.setUsername(username);
			c.setUsernameURI(uri);
			commentRepository.save(c);
			return c;
		}
		return null;
	}


	@Override
	public Boolean deleteComment(long feedId, String username) {
		// TODO Auto-generated method stub
		commentRepository.deleteByUsernameAndNewsfeed(username,new NewsFeed(feedId));
		return true;
	}

}

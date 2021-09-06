package com.pixogram.service;

import org.springframework.stereotype.Service;

import com.pixogram.entity.Comment;
import com.pixogram.entity.FeedActivity;

@Service
public interface FeedActivityService {

	FeedActivity saveLike(long feedId, String username, String uri);

	Boolean deleteLike(long feedId, String username);

	Comment saveComment(long feedId, String username, String comment, String uri);

	Boolean deleteComment(long feedId, String username);

}

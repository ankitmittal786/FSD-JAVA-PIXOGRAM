package com.pixogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.Comment;
import com.pixogram.entity.NewsFeed;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	void deleteByUsernameAndNewsfeed(String username, NewsFeed newsFeed);

}

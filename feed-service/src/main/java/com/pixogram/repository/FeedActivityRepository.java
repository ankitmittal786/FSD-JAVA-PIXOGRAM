package com.pixogram.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pixogram.entity.FeedActivity;
import com.pixogram.entity.NewsFeed;

@Repository
public interface FeedActivityRepository extends JpaRepository<FeedActivity, Long>,CrudRepository<FeedActivity, Long>{

	@Transactional
	void deleteByUsernameAndNewsfeed(String username, NewsFeed newsFeed);

}

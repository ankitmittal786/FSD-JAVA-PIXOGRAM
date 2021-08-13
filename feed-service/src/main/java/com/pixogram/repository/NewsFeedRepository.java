package com.pixogram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.NewsFeed;

@Repository
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Integer>{

	List<NewsFeed> findByUsernameInOrderBypostedDateTimeDesc(List<String> usernames);

	void deleteNewsFeedById(long id);

	List<NewsFeed> findByUsernameInOrderBypostedDateTimeDesc(String username);

}

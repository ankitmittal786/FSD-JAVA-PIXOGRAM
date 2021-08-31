package com.pixogram.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.NewsFeed;

@Repository
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Integer>{

	@Query("Select nf from com.pixogram.entity.NewsFeed nf where nf.username IN (?1) order by nf.postedDate desc")
	List<NewsFeed> findByUsernameInOrderBypostedDateDesc(Set<String> usernames);

	void deleteNewsFeedById(long id);

	@Query("Select nf from com.pixogram.entity.NewsFeed nf where nf.username=?1 order by nf.postedDate desc")
	List<NewsFeed> findByUsernameOrderBypostedDateDesc(String username);

}

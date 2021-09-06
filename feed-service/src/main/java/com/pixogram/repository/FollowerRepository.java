package com.pixogram.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pixogram.entity.FeedActivity;
import com.pixogram.entity.FollowerUser;
import com.pixogram.entity.NewsFeed;

@Repository
public interface FollowerRepository extends JpaRepository<FollowerUser, Long>,CrudRepository<FollowerUser, Long>{

	List<FollowerUser> findByUsername(String username);

	List<FollowerUser> findByFollowingUsername(String username);

	@Query("Select distinct fu.followingUsername from com.pixogram.entity.FollowerUser fu where fu.username=1")
	List<String> findFollowingUsernameByUsername(String username);

	@Query("Select distinct fu from com.pixogram.entity.FollowerUser fu where fu.username in (?1)")
	List<FollowerUser> findByFollowingUsernameIn(List<String> username);

	List<String> findDistinctFollowingUsernameByUsername(String username);

}

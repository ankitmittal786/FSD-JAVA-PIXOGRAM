package com.pixogram.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.FollowerUser;

@Repository
public interface FollowerUserRepository extends JpaRepository<FollowerUser, Long> {

	List<FollowerUser> findByUsername(String username);

	List<FollowerUser> findByFollowingUsername(String username);

	@Query("Select distinct fu.followingUsername from com.pixogram.entity.FollowerUser fu where fu.username=1")
	List<String> findFollowingUsernameByUsername(String username);
	
	@Query("Select distinct fu from com.pixogram.entity.FollowerUser fu where fu.username in (?1)")
	List<FollowerUser> findByFollowingUsernameIn(List<String> username);
}

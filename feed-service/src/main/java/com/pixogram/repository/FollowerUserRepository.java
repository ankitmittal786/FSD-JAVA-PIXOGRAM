package com.pixogram.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.FollowerUser;

@Repository
public interface FollowerUserRepository extends JpaRepository<FollowerUser, Long> {

	int changeStatusOfUser(long id, boolean status);

	Map<String,String> findByFollowerUserId(long userId);

	List<FollowerUser> findByUsername(String username);

	List<FollowerUser> findByFollowingUsername(String username);

	@Query("Select distinct fu.followingUsername from FollowerUser fu where a username=?1")
	List<String> findFollowingUserNameByUsername(String username);
	
	@Query("Select distinct fu from FollowerUser fu where a.username in (?1)")
	List<FollowerUser> findByFollowingUsernameIn(List<String> username);
}

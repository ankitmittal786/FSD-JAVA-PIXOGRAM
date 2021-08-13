package com.pixogram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.BlockedUser;

@Repository
public interface BlockedUserRepository extends JpaRepository<BlockedUser, Long> {

	int changeStatusOfUser(long id, boolean status);

	boolean deleteByUserIdAndBlockedUserId(long userId, long id);

	List<BlockedUser> findByUsername(String username);

}

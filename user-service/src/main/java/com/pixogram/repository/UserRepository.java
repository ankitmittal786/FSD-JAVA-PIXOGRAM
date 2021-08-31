package com.pixogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findById(long id);

//	int changeStatusOfUser(long id, boolean status);

	User findUserByUsername(String username);

	User findByUsernameAndStatusTrue(String name);

	User findByUsernameOrEmailAndStatusTrue(String username, String username2);

}

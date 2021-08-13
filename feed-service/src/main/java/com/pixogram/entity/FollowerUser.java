package com.pixogram.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "follower_user", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "followingUsername" }))
public class FollowerUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6600895949345630986L;

	public FollowerUser(String username, String followUsername, String usernameURI, String followUsernameURI) {
		super();
		this.username = username;
		this.followingUsername = followUsername;
		this.usernameURI = usernameURI;
		this.followUsernameURI = followUsernameURI;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	

	private String  followingUsername;
	
	private String  usernameURI;

	private String  followUsernameURI;


}

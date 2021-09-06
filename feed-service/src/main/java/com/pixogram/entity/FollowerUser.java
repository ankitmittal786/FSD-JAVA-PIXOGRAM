package com.pixogram.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "username", "followingUsername" }))
@AllArgsConstructor
@NoArgsConstructor
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

	public FollowerUser(User user) {
		this.followingUsername = user.getUsername();
		this.followName=user.getFirstName()+" "+user.getLastName();
		this.followUsernameURI = user.getProfilePictureURL();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	

	private String  followingUsername;
	
	private String  usernameURI;

	private String  followUsernameURI;
	
	private String name;
	
	private String followName;


}

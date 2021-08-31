package com.pixogram.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

	public User(long userId) {
		this.id=userId;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	private String description;
	private String email;
	private String gender;
	private Date dateOfBirth;
	private boolean status;
	private String role;
	private String profilePictureURL;
	private Date createdDateTime;
	private long followerCount;
	private long followingCount;

}

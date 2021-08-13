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
	
	private String description;
	
	private boolean status;
	
	private long followerCount;
	
	private long followingCount;
	
	private String profilePictureURL;
	
	private Date createdDateTime;

}

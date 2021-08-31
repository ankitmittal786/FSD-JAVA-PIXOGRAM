package com.pixogram.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	public User(long userId) {
		this.id=userId;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(unique = true, length = 20)
	@Size(max = 20, message = "Max 20 characters are allowed")
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

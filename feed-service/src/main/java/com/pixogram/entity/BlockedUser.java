package com.pixogram.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "blocked_user", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "blockedUsername" }))
public class BlockedUser implements Serializable {

	public BlockedUser(String username, String blockedUsername, String blockedUsernameURI) {
		super();
		this.username = username;
		this.blockedUsername = blockedUsername;
		this.blockedUsernameURI = blockedUsernameURI;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;

	private String  blockedUsername;

	private String  blockedUsernameURI;


}

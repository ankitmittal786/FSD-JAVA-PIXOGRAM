package com.pixogram.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class NewsFeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7497686075030252888L;
	
	@Id
	private int id;
	private int mediaId;
	private String userId;
	private Date postedDateTime;
	

}

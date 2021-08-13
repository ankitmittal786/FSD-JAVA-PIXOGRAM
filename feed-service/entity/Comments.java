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
public class Comments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1995767882482739764L;
	
	@Id
	private long id;
	
	private long newsFeedId;
	private String comment;
	
	private String userId;
	private Date dateTime;


}

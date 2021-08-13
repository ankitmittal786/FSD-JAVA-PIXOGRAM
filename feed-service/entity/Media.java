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
public class Media implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	private long userid;
	private String mediaURL;
	private String mimeType;
	private String mediaTitle;
	private String mediaCaption;
	private Date uploadedDate;
	private Date ModifiedDate;
	private boolean hide;

	
}

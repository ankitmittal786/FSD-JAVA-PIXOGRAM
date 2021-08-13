package com.pixogram.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	

	public Media(String username, @NotNull List<String> fileName, @NotNull List<String> mediaURL, String mimeType,
			Date uploadedDate) {
		super();
		this.username = username;
		this.fileName = fileName;
		this.mediaURL = mediaURL;
		this.mimeType = mimeType;
		this.uploadedDate = uploadedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String username;
	
	@NotNull
	@ElementCollection
	private List<String> fileName;
	
	@NotNull
	@ElementCollection
	private List<String> mediaURL;
	
	private String mimeType;
	
	private Date uploadedDate;
	
}

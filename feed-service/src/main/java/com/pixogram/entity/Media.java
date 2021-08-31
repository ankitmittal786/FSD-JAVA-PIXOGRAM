package com.pixogram.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Media implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public Media(@NotNull String fileName, @NotNull String mediaURL, NewsFeed feed) {
		super();
		this.fileName = fileName;
		this.mediaURL = mediaURL;
		this.newsfeed=feed;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String fileName;
	
	@NotNull
	private String mediaURL;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name="newsfeed_id")
    private NewsFeed newsfeed;
	
}

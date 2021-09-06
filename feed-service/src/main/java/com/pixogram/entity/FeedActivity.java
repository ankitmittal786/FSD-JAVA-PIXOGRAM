package com.pixogram.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "feedactivity")
public class FeedActivity  implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8351221943751707909L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private Date dateTime;

	private String  usernameURI;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name="newsfeed_id")
    private NewsFeed newsfeed;

}

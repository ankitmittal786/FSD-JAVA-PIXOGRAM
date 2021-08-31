package com.pixogram.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

//@NamedEntityGraph(
//	  name = "newfeed",
//	  attributeNodes = {
//	    @NamedAttributeNode("comments"),
//	    @NamedAttributeNode("media"),
//	  }
//	)
@Entity
@Getter
@Setter
@Table(name = "newsfeed")
public class NewsFeed implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7497686075030252888L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonManagedReference
	@OneToMany(mappedBy="newsfeed", cascade = CascadeType.ALL)
	private List<Media> media;
	
	@NotNull
	private String username;
	
	private String usernameUri;
	
	@Column(columnDefinition = "bigint default 0")
	private long likes;
	
	@OneToMany(mappedBy="newsfeed", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
	
	private String description;
	
//	private String location;
	
	private Date postedDate;
	

}

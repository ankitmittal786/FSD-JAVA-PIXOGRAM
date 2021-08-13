package com.pixogram.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@NamedEntityGraph(
	  name = "post-entity-graph",
	  attributeNodes = {
	    @NamedAttributeNode("user"),
	    @NamedAttributeNode("comments")
	  }
	)
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
	
	@NotNull
	private long mediaId;
	
	@NotNull
	private String username;
	
	private long like;
	
	private long likedBy;
	
	@OneToMany(mappedBy = "newsfeed")
    private List<Comment> comments = new ArrayList<>();
	
	private String mediaCaption;
	
	private Date postedDateTime;
	

}

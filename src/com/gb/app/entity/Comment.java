package com.gb.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "body")
	private String body;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "feed_id")
	private Feed feed;
	
	@Column(name = "level")
	private Integer level;

	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "comment")
	private List<CommentVote> votes;

	public Comment(String body, User user, Feed feed, LocalDateTime createdDate) {
		this.body = body;
		this.user = user;
		this.feed = feed;
		this.createdDate = createdDate;
	}

}

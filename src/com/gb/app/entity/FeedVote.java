package com.gb.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "feed_votes")
public class FeedVote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "feed_id")
	private Feed feed;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "is_upvote")
	private Boolean isUpVote;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	public FeedVote(Feed feed, User user, Boolean isUpVote, LocalDateTime createdDate) {
		this.feed = feed;
		this.user = user;
		this.isUpVote = isUpVote;
		this.createdDate = createdDate;
	}

}

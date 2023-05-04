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
@Table(name = "feeds")
public class Feed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "body")
	private String body;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "feed")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "feed")
	private List<FeedVote> votes;

	public Feed(String body, User user, LocalDateTime createdDate) {
		this.body = body;
		this.user = user;
		this.createdDate = createdDate;
	}

}

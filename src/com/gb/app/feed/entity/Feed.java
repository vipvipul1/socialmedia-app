package com.gb.app.feed.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="body")
	private String body;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;

	public Feed(String body, String userId, LocalDateTime createdDate) {
		this.body = body;
		this.userId = userId;
		this.createdDate = createdDate;
	}
	
}

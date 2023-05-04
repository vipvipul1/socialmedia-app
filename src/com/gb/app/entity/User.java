package com.gb.app.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "user")
	private List<Feed> feeds;
	
	@ManyToMany
	@JoinTable(name = "follows", 
		joinColumns = @JoinColumn(name = "follower_user_id"), 
		inverseJoinColumns = @JoinColumn(name = "following_user_id"))
	private Set<User> followingsSet;
	
	@ManyToMany(mappedBy = "followingsSet")
	private Set<User> followersSet;

	public User(String name, String phone, String email, String password, LocalDateTime createdDate) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User))
			return false;
		User user = (User) obj;
		if (Objects.equals(this.getId(), user.getId()))
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
}

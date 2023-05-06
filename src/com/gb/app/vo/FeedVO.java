package com.gb.app.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedVO {
	private Integer feedId;
	private String feedBody;
	private String postedBy;
	private Long upVotesCount;
	private Long downVotesCount;
	private Long commentsCount;
	private String postDate;
	private List<CommentVO> comments;
}

package com.gb.app.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVO {
	Integer commentId;
	String commentBody;
	Long upVotesCount;
	Long downVotesCount;
	String commentedBy;
	String commentDate;
	List<CommentVO> childComments;
}

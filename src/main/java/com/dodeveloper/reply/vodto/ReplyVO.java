package com.dodeveloper.reply.vodto;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReplyVO {
	private int bno; // 게시글 번호
	private int replyNo; // 댓글 번호
	private String replyer; // 댓글 작성자
	private Timestamp writtenDate; // 댓글 작성한 날짜
	private String replyContent; // 댓글 내용
	private int bType; // 게시판 구분
}

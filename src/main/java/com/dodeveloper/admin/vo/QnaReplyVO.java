package com.dodeveloper.admin.vo;

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
public class QnaReplyVO {
	private int replyNo;
	private int bNo;
	private String replyer;
	private Timestamp writtenDate;
	private String replyContent;
	private int bType;
	
}

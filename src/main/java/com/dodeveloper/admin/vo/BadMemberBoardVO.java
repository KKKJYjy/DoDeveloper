package com.dodeveloper.admin.vo;

import java.security.Timestamp;

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
public class BadMemberBoardVO {
	private int no;
	private String userId;
	private int boardNo;
	private int penaltyCnt;
	private String suspendReason;
	private Timestamp registerDate;
	private Timestamp releaseDate;
	private String status;
}

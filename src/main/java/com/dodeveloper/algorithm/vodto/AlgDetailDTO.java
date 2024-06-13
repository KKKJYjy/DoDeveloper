package com.dodeveloper.algorithm.vodto;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class AlgDetailDTO {
	private int algDetailNo;
	private int algBoardNo;
	private Timestamp algDetailPostDate;
	private String writer;
	private String algDetailContent;
	private String algDetailResult;
	private String algDetailTitle;
	private String algDetailComment;
	private int boardType;
	private String isDeleted;
}

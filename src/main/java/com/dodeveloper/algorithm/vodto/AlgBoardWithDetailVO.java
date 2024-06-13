package com.dodeveloper.algorithm.vodto;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
	* @author : yeonju
	* @date : 2024. 6. 11. 
	* @description : 알고리즘 + 알고리즘 상세 join하여 메인홈에 출력하기 위해서 만든 VO객체
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class AlgBoardWithDetailVO {
	private int boardNo;
	private int classificationCode;
	private String title;
	private String comment;
	private int algDetailNo;
	private int algBoardNo;
	private Timestamp algDetailPostDate;
	private String writer;
	private String algDetailContent;
	private String algDetailResult;
	private String algDetailTitle;
	private String algDetailComment;
	private int boardType;
}

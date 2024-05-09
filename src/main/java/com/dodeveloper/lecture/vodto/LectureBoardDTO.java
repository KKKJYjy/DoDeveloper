package com.dodeveloper.lecture.vodto;

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
public class LectureBoardDTO {
	private int lecNo; // 글 번호
	private String lecTitle; // 제목
	private String lecReview; // 후기
	private String lecWriter; // 작성자
	private Timestamp lecPostDate; // 작성일시
	private int lecScore; // 별점
	private String lecLink; // 링크
}
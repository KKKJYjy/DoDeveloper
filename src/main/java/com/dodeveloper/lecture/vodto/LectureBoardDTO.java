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
	private int lecNo;
	private String lecTitle;
	private String lecReview;
	private String lecWriter;
	private Timestamp lecPostDate;
	private int lecReadCount;
	private int lecLikeCount;
}
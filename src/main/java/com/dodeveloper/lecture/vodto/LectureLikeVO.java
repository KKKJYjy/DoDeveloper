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
public class LectureLikeVO {
	private int lecLikeNo;
	private int lecNo;
	private String lecLikeUser;
	private String lecLikeTitle;
	private Timestamp lecLikeDate;
}

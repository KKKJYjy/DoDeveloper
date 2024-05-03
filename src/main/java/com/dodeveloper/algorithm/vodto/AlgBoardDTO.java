package com.dodeveloper.algorithm.vodto;

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
public class AlgBoardDTO {
	private int boardNo;
	private int classificationCode;
	private String title;
	private String comment;
	
}

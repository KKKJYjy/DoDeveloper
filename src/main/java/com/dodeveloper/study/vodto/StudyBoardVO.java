package com.dodeveloper.study.vodto;

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
public class StudyBoardVO {
	private String stuWriter;
	private String stuTitle;
	private String stuContent;
	private String stuLoc;
	private double stuX;
	private double stuY;
	private String stuDate;
	private String stuPers; //이거 디비에도 변경
	private String contactLink;
	private String status;
	private Timestamp endDate;
	private int readCount;
	private int scrape;
}

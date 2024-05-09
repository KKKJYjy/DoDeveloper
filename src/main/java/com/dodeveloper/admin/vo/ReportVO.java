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
public class ReportVO {
	private int reportNo;
	private int category;
	private int boardNo;
	private String writer;
	private Timestamp reportDate;
	private String reportReason;
	private String repoter;
	private String isDelete;
}
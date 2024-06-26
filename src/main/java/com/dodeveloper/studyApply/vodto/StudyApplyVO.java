package com.dodeveloper.studyApply.vodto;

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
public class StudyApplyVO {
	private int applyNo;
	private String applyId;
	private int stuNo;
	private Timestamp applyDate;
	private String reason;
	private String status;
}

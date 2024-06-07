package com.dodeveloper.studyApply.vodto;

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
public class StudyApplyDTO {
	private int applyNo;
	private String applyId;
	private int stuNo;
	private String reason;
}

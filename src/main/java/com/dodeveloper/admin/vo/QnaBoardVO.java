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
public class QnaBoardVO {
	private int no;
	private String qnaWriter;
	private String qnaTitle;
	private String qnaContent;
	private Timestamp postDate;
	private int bType;
}

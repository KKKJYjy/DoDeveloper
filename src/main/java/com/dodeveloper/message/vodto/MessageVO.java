package com.dodeveloper.message.vodto;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MessageVO {
	private int messageNo;
	private String writer;
	private String title;
	private String content;
	private Timestamp writeDate;
	private String isDeleted;
	
}

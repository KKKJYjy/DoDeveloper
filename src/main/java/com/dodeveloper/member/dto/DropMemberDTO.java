package com.dodeveloper.member.dto;

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
public class DropMemberDTO {
	private String userId;
	private Timestamp dropApplyDate;
	private String dropReason;
	private String status;
}

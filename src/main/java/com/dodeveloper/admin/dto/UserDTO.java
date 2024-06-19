package com.dodeveloper.admin.dto;

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
public class UserDTO {
	private String userId;
	private String userName;
	private String email;
	private Timestamp registerDate;
	private Integer penaltyCnt;
	private Timestamp suspendEnd;
	private String status;
}

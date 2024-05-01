package com.dodeveloper.member.vo;

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
public class MemberVO {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String careers;
	private String prefix;
	private byte[] profileImage;
	private Timestamp registerDate;
	private String isAdmin;
	private String sessionVal;
	private String sessionLimit;
	private String status;
}

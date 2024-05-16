package com.dodeveloper.study.vodto;


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
public class StuStackDTO {
	//db에 저장된 stuStack테이블의 정보를 가져올때 사용하는 DTO
	private int stuStackNo;
	private int stuBoardNo;
	private int chooseStack;
	private String stackName;
}

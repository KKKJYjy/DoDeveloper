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
	private int stuStackNo;
	private int stuBoardNo;
	private int chooseStack;
	private String stackName;
}

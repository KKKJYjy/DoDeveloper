package com.dodeveloper.company.vodto;

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
public class WrittenCompanyBoardDTO {
	private int revNo;
	private String revWriter;
	private String revTitle;
	private String revProfession;
	private String revContent;
	private String revGood;
	private String revBed;
	private int companyInfoNo;
	private int bType;

}

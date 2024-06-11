package com.dodeveloper.company.vodto;

import java.security.Timestamp;

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
public class CompanyInfoWithRevVO {
	private int companyInfoNo;
	private String companyInfoName;
	private String companyInfoImgLogo;
	private String companyInfoLocation;
	private String companyInfoFields;
	private int revNo;
	private String revWriter;
	private String revTitle;
	private String revProfession;
	private String revContent;
	private String revGood;
	private String revBed;
	private Timestamp revPostDate;
	private int bType;
}

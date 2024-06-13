package com.dodeveloper.company.vodto;

import java.security.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
	* @author : yeonju
	* @date : 2024. 6. 11.
	* @description : 기업정보 + 기업리뷰 join하여 메인홈에 출력하기 위해 만든 VO객체
 */
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

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
public class CompanyInfoVO {
	private int companyInfoNo;
	private String companyInfoName;
	private String companyInfoImgLogo;
	private String companyInfoLocation;
	private String companyInfoFields;
}

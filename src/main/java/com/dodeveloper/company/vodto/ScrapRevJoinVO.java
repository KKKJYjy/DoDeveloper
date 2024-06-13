package com.dodeveloper.company.vodto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ScrapRevJoinVO {
	private int revNo;
	private String companyInfoImgLogo;
	private String companyInfoName;
	private String revTitle;
	private Timestamp scrapDate;

}

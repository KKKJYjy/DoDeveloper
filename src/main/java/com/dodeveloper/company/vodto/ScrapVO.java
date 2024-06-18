package com.dodeveloper.company.vodto;

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
public class ScrapVO {
	private int scrapNo;
	private int scrapBoard;
	private String scrapId;
	private Timestamp scrapDate;
	private int bType;
	private String scrapLecTitle;
}

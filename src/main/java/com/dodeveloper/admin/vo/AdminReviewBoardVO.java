package com.dodeveloper.admin.vo;

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
public class AdminReviewBoardVO {
    private int revNo;
    private String revWriter;
    private Timestamp revPostDate;
    private String revTitle;
    private String revProfession;
    private String revContent;
    private String revGood;
    private String revBed;
    private int companyInfoNo;
    private int btype;
}

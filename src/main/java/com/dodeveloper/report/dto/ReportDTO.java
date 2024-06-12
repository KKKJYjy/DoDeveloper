package com.dodeveloper.report.dto;

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
public class ReportDTO {
    private int reportNo;
    private String category;
    private int boardNo;
    private String writer;
    private Timestamp reportDate;
    private String reportReason;
    private String reporter;
    private String isDelete;
    private int btypeNo;
    
    public ReportDTO(String category, int boardNo, String writer, String reportReason, String reporter, int btypeNo) {
	super();
	this.category = category;
	this.boardNo = boardNo;
	this.writer = writer;
	this.reportReason = reportReason;
	this.reporter = reporter;
	this.btypeNo = btypeNo;
    }

    
    
    
}

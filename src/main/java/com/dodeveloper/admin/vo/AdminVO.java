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
public class AdminVO {
    private int stuNo;
    private String stuWriter;
    private String stuTitle;
    private String stuContent;
    private String stuLoc;
    private String stuDate;
    private String status;
    private Timestamp endDate;

}

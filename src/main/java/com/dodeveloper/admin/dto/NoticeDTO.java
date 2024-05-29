package com.dodeveloper.admin.dto;

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
public class NoticeDTO {
    private int boardNo;
    private String writer;
    private Timestamp postDate;
    private String title;
    private String content;
    private int readCount;
}

package com.dodeveloper.lecture.vodto;

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
public class LectureSearchDTO {
	private String searchType; // 검색 조건
	private String searchValue; // 검색 값
	private String filterType; // 검색 필터(최신순 / 인기순 / 조회순)
}

package com.dodeveloper.study.vodto;

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
public class StuStackVO {
    // db에 stuStack테이블에 새로 저장할때 사용하는 VO
    private int stuBoardNo;
    private int[] chooseStack;
}

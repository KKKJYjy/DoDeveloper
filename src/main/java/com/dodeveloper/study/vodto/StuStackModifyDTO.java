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
public class StuStackModifyDTO {
    // db에 저장된 stuStack을 수정할때 (삭제하고, 새로 인서트) 사용하는 DTO
    private int[] stuStackNo;
    private int stuBoardNo;
    private int[] chooseStack;

}

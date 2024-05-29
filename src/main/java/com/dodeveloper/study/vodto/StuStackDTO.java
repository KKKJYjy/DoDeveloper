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
public class StuStackDTO {
    // db에 저장된 stuStack테이블의 정보를 가져올때 사용하는 DTO
    private int stuStackNo;
    private int stuBoardNo;
    private int chooseStack;
    private String stackName;

    // 게시글 수정시 스터디 언어 수정할때 필요한 플래그 멤버 변수
    private boolean isDelete; // 게시글 수정시 유저가 삭제 처리하겠다 표시한 언어 (true: 수정 누르면 DB에서 삭제 false: 아무것도 안함)
    private boolean isNew; // 게시글 수정시 새롭게 추가된 언어 (true: 수정버튼 누르면 새롭게 insert, false: 아무것도안함)
}

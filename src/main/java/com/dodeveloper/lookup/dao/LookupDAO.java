package com.dodeveloper.lookup.dao;

public interface LookupDAO {
    // 해당 유저가 ?게시판 ?번글을 언제 읽었는지 확인하는 메서드 (공통)
    int selectDiff(String readWho, int boardNo, int bType) throws Exception;

    // 해당 유저가 ?게시판 ?번 게시글을 조회했다는 이력을 insert 하는 메서드 (공통)
    int insertLookup(String readWho, int boardNo, int bType) throws Exception;

    // 스터디 게시판의 ?번글의 조회수를 올리는 메서드 (각자구현)
    int updateLookupStudyBoard(int stuNo) throws Exception;
}

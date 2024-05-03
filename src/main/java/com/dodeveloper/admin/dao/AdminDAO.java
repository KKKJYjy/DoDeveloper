package com.dodeveloper.admin.dao;

import java.util.List;


import com.dodeveloper.admin.vo.ReportVO;
import com.dodeveloper.member.vo.MemberVO;

public interface AdminDAO {
	// ��ü ȸ�� ��ȸ�ϴ� �޼���
	List<MemberVO> selectMember() throws Exception;
	
	// �Ű� �Խ��� ��ȸ�ϴ� �޼���
	List<ReportVO> selectReport() throws Exception;
	
}

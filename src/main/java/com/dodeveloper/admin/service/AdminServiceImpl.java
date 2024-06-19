package com.dodeveloper.admin.service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dodeveloper.admin.dao.AdminDAO;

import com.dodeveloper.admin.dto.ConnectLogDTO;
import com.dodeveloper.admin.dto.UserDTO;
import com.dodeveloper.admin.dto.UserStatusDTO;
import com.dodeveloper.admin.vo.AdminVO;
import com.dodeveloper.admin.vo.BadMemberBoardVO;
import com.dodeveloper.admin.vo.ConnectLogVO;
import com.dodeveloper.admin.vo.CountUriVO;
import com.dodeveloper.study.vodto.StudyBoardVO;


import com.dodeveloper.admin.vo.BadMemberBoardVO;

import com.dodeveloper.member.vo.MemberVO;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO aDao;
	
	


	
	public List<UserDTO> getAllUser() throws Exception {
		// System.out.println("서비스단 유저 조회");
		
		List<UserDTO> userList = aDao.selectAllUser();
		
		return userList;

	}

	// 유저 상태 변경하며, 정지일과 해제일 등록
	@Transactional
    public boolean modifyUserStatus(String userId, String newStatus) {
        try {
            // 현재 날짜 구하기
        	Date suspendStart = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(suspendStart);

            // 정지 해제일 계산 (현재 날짜 + 7일)
            Calendar cal = Calendar.getInstance();
            cal.setTime(suspendStart);
            cal.add(Calendar.DAY_OF_MONTH, 7);
            Date suspendEnd = cal.getTime();
           //  System.out.println("해제일" + suspendEnd);

            
            // 사용자 상태 업데이트
            Map<String, Object> userParams = new HashMap<>();
            userParams.put("userId", userId);
            userParams.put("newStatus", newStatus);
          //   System.out.println("유저와 변경된 상태 : " + userParams);
            aDao.updateUserStatus(userParams);

            
            // 패널티 테이블에서 정지일자와 해제일 업데이트
            if ("정지회원".equals(newStatus)) {
                Map<String, Object> penaltyParams = new HashMap<>();
                penaltyParams.put("userId", userId);
                penaltyParams.put("suspendStart", formattedDate);
                penaltyParams.put("suspendEnd", suspendEnd);
               //  System.out.println("정지된 유저와 정지일, 해제일 : " + penaltyParams);
                aDao.updatePenaltyRecord(penaltyParams);
                
            } else if ("탈퇴회원".equals(newStatus)) {
                // 탈퇴회원 처리
                aDao.banUser(userId);
            }

            return true;
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return false;
        }
    }
	

	

	@Override
	public int getConnectLog(ConnectLogDTO connectLog) throws Exception {
		// System.out.println("접속기록 서비스단 호출" + connectLog);
		
		int result = aDao.insertConnectLog(connectLog);
		
		return result;
	}

	@Override
	public List<CountUriVO> getPageLogCount() throws Exception {
		
		List<CountUriVO> result = aDao.selectPageLog();
		
		
		return result;
	}

	@Override
	public List<ConnectLogVO> getDateLog(int month) throws Exception {
		
		List<ConnectLogVO> result = aDao.connectDateLog(month);
		
		for(ConnectLogVO c : result) {
			// System.out.println(c.toString());
		}
		
		return result;
	}

	

}


package com.dodeveloper.lecture.vodto;

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
public class LectureBoardVO {
	private int lecNo; // �� ��ȣ
	private int bType; // �Խ��� ����
	private String lecTitle; // ����
	private String lecReview; // �ı�
	private String lecWriter; // �ۼ���
	private Timestamp lecPostDate; // �ۼ�����
	private int lecReadCount; // ��ȸ��
	private int lecLikeCount; // ���ƿ� ��
	private int lecScore; // ����
	private String lecLink; // ��ũ
	private int scrap; // ��ũ��
}

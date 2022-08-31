package com.prac.utube.service;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.prac.utube.vo.Member;

public interface MemberService {

	//  회원가입 멤버 등록
	int MemberJoin(Member member, MultipartFile multi);
	
	
	// db 아이디값 여부 확인
	Map<String, Object> login(String user_email, String user_password);
	
	// 아이디 유무 확인
	Member userExists(String user_email);
	
	// 계저 정보
	Member selectuser(String user_email);
	
	// 수정
	int memberUpdate(Member member, MultipartFile multi);
	
	// 사용자 전체목록
	ArrayList<Object> getMemberList();
	
	//회원 1개 삭제
	int deluser(String delUser);
}

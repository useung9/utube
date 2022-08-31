package com.prac.utube.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.prac.utube.vo.Membersub;

public interface MembersubService {
	
	// 로그인 사용자에 대한 구독정보 출력
	List<Membersub> sublist(String user_email);
	
	// 계정 구독 추가
	int addSub(Membersub membersub, MultipartFile multi);
	
}

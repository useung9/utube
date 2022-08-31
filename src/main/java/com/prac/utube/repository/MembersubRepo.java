package com.prac.utube.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.utube.vo.Membersub;

@Repository
public class MembersubRepo {

	@Autowired
	private SqlSession sqlsession;
	
	// 계정별 구독 정보 가져오기
	public List<Membersub> sublist(String user_email){
		return sqlsession.selectList("membersub.sublist", user_email);
	}
	
	// 게정 구독 추가하기
	public int addSub(Membersub membersub) {

		return sqlsession.insert("membersub.addsub", membersub);
		
	}
	
}

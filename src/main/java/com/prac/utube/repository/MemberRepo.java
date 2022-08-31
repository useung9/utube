package com.prac.utube.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.utube.vo.Member;

@Repository
public class MemberRepo {
	
	@Autowired
	private SqlSession sqlsession;
	
	
	
	// 등록
	public int memberInsert(Member member) {
		return sqlsession.insert("member.memberInsert", member);
	}
	
	
	// 중복 확인
	public Member memberSelect(String user_email) {

		return sqlsession.selectOne("member.memberSelect", user_email);
	}

	
	// 업데이트
	public int memberUpdate(Member member) {

		return sqlsession.update("member.memberUpdate", member);
	}
	
	// 사용자 전체목록
	public ArrayList<Object> getMemberList(){
		ArrayList<Object> memberList = (ArrayList<Object>) sqlsession.selectList("member.getMemberList");
		return memberList;
	}

	// 회원삭제 1건
	public int delUser(String delUser) {
		
		return sqlsession.delete("member.delUser", delUser);
	}
	
}

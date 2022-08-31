package com.prac.utube.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.prac.utube.vo.Board;
import com.prac.utube.vo.BoardReply;
import com.prac.utube.vo.Expression;

public interface BoardService {
	// 게시물등록
	
	int addBoard(Board board);
	
	// 계정별 등록 유투브 내용
	ArrayList<Object>getUserBoard();

	// 유튜브 정보 가져오기
	Board getBoardDetail(String key);
	
	// 게시판 별 댓글 리스트 
	ArrayList<Object> getboard_ReplyList(String boardIdx);
	
	// 댓글 삽입
	int board_ReplyAdd(Map<String, Object> reqData);
	
	// 전제 게시물 조회
	ArrayList<Object> getboardList();
	
	
	// 게시물 조회수 증가
	int addViews(String key);
	
	// 좋아요 후 정보 읽어오기
	Board boardLikes(Map<String, Object> reqMap);
	
	
	// 싫어요 후 정보 읽어오기
	Board boardDisLikes(Map<String, Object> reqMap);
	// 표현 체크 
	Expression expressionCheck(Map<String, Object> reqMap);
	
	// 대댓글
	int board_reReplyAdd(HashMap<String, Object> reqMap);

	// 전체영상 리스트
	ArrayList<Object> boardDataList();
	
	//댓글의 level 최대값
	 int board_maxRelevel(int board_idx);
	 
	 ArrayList<Object> board_SearchData(String reqParam);
}

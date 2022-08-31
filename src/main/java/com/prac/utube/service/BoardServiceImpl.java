package com.prac.utube.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prac.utube.repository.BoardRepo;
import com.prac.utube.vo.Board;
import com.prac.utube.vo.BoardReply;
import com.prac.utube.vo.Expression;

@Service
public class BoardServiceImpl implements BoardService{

	
	
	@Autowired
	private BoardRepo brepo;
	
	// 게시판 추가
	@Override
	public int addBoard(Board board) {
			
		
		String splitarr[] = board.getBoard_thum().split("=");
		String inputboard = splitarr[1];
		board.setBoard_thum(inputboard);
		System.out.println("Service - board : " + board);
		
		int result = brepo.boardInsert(board);

		return result;
	}
		
		

	// 계정별 등록한내용
	@Override
	public ArrayList<Object> getUserBoard() {

		return brepo.getUserBoard();
			
		
	}



	// 보드 게시판
	@Override
	public Board getBoardDetail(String key) {
			
		return brepo.getBoardDetail(key); 
	}


	// 게시판 별 댓글 리스트업
	@Override
	public ArrayList<Object> getboard_ReplyList(String boardIdx) {

		return brepo.getboard_Replylist(boardIdx);
	}


	// 댓글 추가(부모)
	@Override
	public int board_ReplyAdd(Map<String, Object> reqData) {
		
		return brepo.board_ReplyAdd(reqData);
	}


	// 게시물 전체 긁어오기
	@Override
	public ArrayList<Object> getboardList() {

		
		return brepo.getboardList();
	}



	// 게시물 조회수 증가
	@Override
	public int addViews(String key) {
		
		return brepo.addViews(key);
	}


	// 좋아요
	@Override
	public Board boardLikes(Map<String, Object> reqMap) {
			for(String key : reqMap.keySet()) {
				System.out.println("============key : " + key +" val : "+ reqMap.get(key));
			}
			Board board = brepo.boardLikes(reqMap);
		return board;
	}


	// 표현 체크
	@Override
	public Expression expressionCheck(Map<String, Object> reqMap) {

		return brepo.expressionCheck(reqMap);
	}


	// 싫어요.
	@Override
	public Board boardDisLikes(Map<String, Object> reqMap) {
		
		return brepo.boardDislikes(reqMap);
	}


	
	
	// 대댓글 등록
	@Override
	public int board_reReplyAdd(HashMap<String, Object> reqMap) {

		return brepo.board_reReplyAdd(reqMap);
	}



	@Override
	public ArrayList<Object> boardDataList() {

		return brepo.boardDataList();
	}


	// 댓글의 최대 level 구해오기
	@Override
	public int board_maxRelevel(int board_idx) {

		return brepo.board_maxRelevel(board_idx);
	}



	@Override
	public ArrayList<Object> board_SearchData(String reqParam) {

		return brepo.board_SearchData(reqParam);
	}


	

}

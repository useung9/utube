package com.prac.utube.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prac.utube.vo.Board;
import com.prac.utube.vo.BoardReply;
import com.prac.utube.vo.Expression;

@Repository
public class BoardRepo {
	@Autowired
	private SqlSession sqlsession;

	// 게시물 추가
	public int boardInsert(Board board) {
		System.out.println("repo - board : " + board);
		return sqlsession.insert("board.addBoard", board);
	}

	// 게정별 등록한 유튜브 내용 읽어오기
	public ArrayList<Object> getUserBoard() {
		ArrayList<Object> result = (ArrayList<Object>) sqlsession.selectList("board.getUserBoard");
		System.out.println("================== db result : " + result);
		return result;
	}

	// 보드 게시판 상세보기
	public Board getBoardDetail(String key) {

		return sqlsession.selectOne("board.getBoardDetail", key);
	}

	// 게시판 번호에 맞는 댓글 리스트 가져오기
	public ArrayList<Object> getboard_Replylist(String boardIdx) {
		int numboardIdx = (int) Integer.parseInt(boardIdx);

		System.out.println("--------------------------------  mybatis 파라미터 : " + boardIdx);

		ArrayList<Object> result = (ArrayList<Object>) sqlsession.selectList("board.getboard_ReplyList", numboardIdx);
		System.out.println("reposi : " + result);
		return result;

	}

	// 댓글 추가(부모)
	public int board_ReplyAdd(Map<String, Object> reqData) {
		/*
		 * 등록 필요 데이터 게시물 번호, 작성자, 내용, step, level "boardidx":boardidx, "writer": writer,
		 * "content": content, "restep":1, "level":1
		 */

		return sqlsession.insert("board.board_ReplyAdd", reqData);
	}

	// 게시판 목록 긁어오기
	public ArrayList<Object> getboardList() {
		ArrayList<Object> result = (ArrayList<Object>) sqlsession.selectList("board.board_getboardList");
		return result;
	}

	// 게시물 조회수 증가
	public int addViews(String key) {
		int board_idx = Integer.parseInt(key);
		return sqlsession.update("board.addViews", board_idx);
	}

	// 좋아요 클릭 처리
	public Board boardLikes(Map<String, Object> reqMap) {
		// 1일 경우 조회수 증가. 1인경우 좋아요를 누르지 않은상태 before
		int boardkey = Integer.parseInt(reqMap.get("boardkey").toString());
		String useremail = reqMap.get("user_email").toString();
		System.out.println("board idx : " + boardkey);
		Expression exp = new Expression();
		exp.setUser_email(useremail);
		exp.setBoardidx(boardkey);
		System.out.println("repo reqMap value : "+reqMap);
		// 1 = 기본에서 좋아요 클릭시.
		if (reqMap.get("cnt").toString().equals("1")) {
			// 좋아요 증가
			sqlsession.update("board.boardLikesAdd", boardkey);
			reqMap.put("user_expression", "1");
			// 좋아요 flag 증가
			sqlsession.insert("board.AddExpression", reqMap);
			// 만약 싫어요가 되어있다면.
		} else if (reqMap.get("cnt").toString().equals("2")) {
			System.out.println("cnt 값 : 2");
		
			sqlsession.update("board.boardLikesAdd", boardkey);
			System.out.println("좋아요 버튼 클릭 : 좋아요 +1");
			// expression 업데이트 좋아요로
			
			// 싫어요로 되어있던 값 없애기
			sqlsession.update("board.boardDislikesSub", boardkey);
			
			System.out.println("싫어요 되어있기에 싫어요 -1");
			// 싫어요 없앤후 좋아요 증가.
			exp.setUser_expression(1);
			sqlsession.update("board.UpExpression", exp);
			System.out.println("exp value : " + exp);
		}
		// -1 : 눌러진 상태에서 다시 누를때 좋아요 취소
		else {
			// 좋아요 수 가져오기
			System.out.println("cnt : -1 : 좋아요 버튼 눌린상태에서 취소 : 좋아요 -1");
			int likecnt = sqlsession.selectOne("board.likescnt", boardkey);
			System.out.println("likeCnt ============== " + likecnt);
			if (likecnt > 0) {
				int up = sqlsession.update("board.boardLikesSub", boardkey);
				System.out.println("update 후 값 : "+ up);
				int del = sqlsession.delete("board.DelExpression", exp);
				System.out.println("delete 후 값 : " + del);
			}
			// 좋아요 flag 감소.

		}
		Board board = getBoardDetail(reqMap.get("boardkey").toString());
		System.out.println("출력 전 board 정보 읽어오기 : " + board);
		return board;
	}

	// 싫어요 클릭처리
	public Board boardDislikes(Map<String, Object> reqMap) {

		int boardkey = Integer.parseInt(reqMap.get("boardkey").toString());
		String useremail = reqMap.get("user_email").toString();
		System.out.println("board idx : " + boardkey);
		Expression exp = new Expression();
		exp.setUser_email(useremail);
		exp.setBoardidx(boardkey);
		System.out.println("dislike repo reqMap value : "+reqMap);
		// 1 = 싫어요가 before 상태
		if (reqMap.get("cnt").toString().equals("1")) {
			System.out.println("reqMap cnt == 1");
			// 좋아요 증가
			sqlsession.update("board.boardDisLikesAdd", boardkey);
			reqMap.put("user_expression", "0");
			// 좋아요 flag 증가
			sqlsession.insert("board.AddExpression", reqMap);
			
			// 좋아요가 이미 되어있는 상태 : 좋아요 -1, 싫어요 +1 
		} else if (reqMap.get("cnt").toString().equals("2")) {
			System.out.println("reqMap cnt == 2 + reqmap : "+reqMap);
			// 좋아요 -1
			sqlsession.update("board.boardLikesSub", boardkey);
			// 싫어요 +1
			sqlsession.update("board.boardDisLikesAdd", boardkey);

			// expression 업데이트 좋아요로
			exp.setUser_expression(0);
			sqlsession.update("board.UpExpression", exp);

		}
		// -1 : 싫어요 after  (= 싫어요 취소)
		else {
			System.out.println("reqMap cnt == else");
			// 싫어요  -1
			System.out.println("싫어요 되어있는 상태에서 클릭 :싫어요 cnt -1");
			sqlsession.update("board.boardDislikesSub", boardkey);
			
			// 표현 row 삭제
			sqlsession.delete("board.DelExpression", exp);
			}
			// 

		Board board = getBoardDetail(String.valueOf(boardkey));
		
		return board;
		}
	

	// 계정별 표현 체크
	public Expression expressionCheck(Map<String, Object> reqMap) {
		System.out.println("express Check : " + reqMap);
		Expression req = new Expression();
		req.setUser_email(reqMap.get("user_email").toString());
		req.setBoardidx(Integer.valueOf(reqMap.get("boardidx").toString()));
		System.out.println("파라미터 vo" + req);

		Expression list = sqlsession.selectOne("board.ExpressionList", req);
		System.out.println("repo List" + list);
		return list;
	}

	// 대댓글 등록
	public int board_reReplyAdd(HashMap<String, Object> reqMap){
		System.out.println("대댓글 등록전 데이터 출력" );
		for(String key : reqMap.keySet()) {
			System.out.println("key : " + key +" / Data : "+(reqMap.get(key)));

		}
		
		System.out.println("대댓글 등록" + reqMap);
	
			int result = sqlsession.insert("board.board_reReplyAdd", reqMap);
			System.out.println(result == 1 ?"정상 등록":"등록실패" );
		return result;
	}
	
	
	// 전체 영상 리스트
	public ArrayList<Object> boardDataList() {
		ArrayList<Object> DataList = (ArrayList<Object>)sqlsession.selectList("board.getUserBoard");
		return DataList;
	}

	
	// 최대 reLevel
	public int board_maxRelevel(int board_idx) {
		BoardReply board = new BoardReply();
		board.setBoard_idx(board_idx);
		Integer value = sqlsession.selectOne("board.board_maxRelebel",board);
		
		System.out.println(value == null ? 0 : value);
		
		
		return 0;
	}

	public ArrayList<Object> board_SearchData(String reqParam) {
		ArrayList<Object> result = (ArrayList<Object>) sqlsession.selectList("board.board_SearchData", reqParam);
		System.out.println("결과값 "+result);
		return result;
	}

}

package com.prac.utube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prac.utube.service.BoardService;
import com.prac.utube.service.BoardServiceImpl;
import com.prac.utube.vo.Board;
import com.prac.utube.vo.BoardReply;
import com.prac.utube.vo.Expression;

@Controller
public class BoardController {
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardServiceImpl bservice;

	@RequestMapping(value = "/board/addboard", method = RequestMethod.GET)
	public String addboardGet(HttpServletRequest request) {

		return "board/addboard";
	}

	@RequestMapping(value = "board/addboard", method = RequestMethod.POST)
	public String addboardPost(HttpServletRequest request, @ModelAttribute("Board") Board board) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ - board" + board);

		

		int result = bservice.addBoard(board);
		System.out.println((result == 1) ? "게시물 정상 등록" : "등록실패");
		return "redirect:/";
	}

	@RequestMapping(value = "board/getUserBoard", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Object> getUserBoard(HttpServletRequest request) {
		//String user_email = request.getParameter("user_email");
		//System.out.println("--------------------------------  ajax 파라미터 아이디 : " + user_email);
		ArrayList<Object> blist = bservice.getUserBoard();
		System.out.println("=============================="+blist);
		return blist;
	}
	
	
	// 게시판 detail page
	@RequestMapping(value="board/boardDetail", method=RequestMethod.GET)
	public String board_detail(HttpServletRequest request, Model model) {
		String key = request.getParameter("boardkey");
		System.out.println("게시판 key 값 : "+ key);
		System.out.println("조회수 증가");
		int result = bservice.addViews(key);
		System.out.println(key+" 번 게시판 addViews 조회수 증가"+ result);
		Board board = bservice.getBoardDetail(key);
		model.addAttribute("board", board);
		return "board/boardDetail";
	}
	
	// 로그인 사용자 게시물 체크 여부
	@RequestMapping(value="board/expressionCheck", method=RequestMethod.GET)
	public @ResponseBody Expression expressionCheck(HttpServletRequest request){
		System.out.println("좋아요 체크 부분"+request.getParameter("boardidx"));
		System.out.println("좋아요 체크 부분"+request.getParameter("user_email"));
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("boardidx",request.getParameter("boardidx"));
		reqMap.put("user_email",request.getParameter("user_email"));
		
		Expression express = bservice.expressionCheck(reqMap);
		System.out.println("list"+express);
		return express;
		
	}
	
	
	// board-replyList 게시물에 맞는 댓글 리스트 읽어오기.
	@RequestMapping(value="board/board_ReplyList", method=RequestMethod.GET)
	public @ResponseBody ArrayList<Object> board_replyList(HttpServletRequest request) {
			System.out.println("-----댓글 리스트 --------------------------------------------"+request.getParameter("boardidx"));
			String boardIdx = request.getParameter("boardidx");
			ArrayList<Object> replyList = bservice.getboard_ReplyList(boardIdx);
			System.out.println("댓글 리스트 크기: " + replyList.size());
			
				System.out.println("Key  : " + replyList);
			
		return replyList;
	}
	
	// 댓글 추가 댓글 작성(부모) 
	@RequestMapping(value="board/board_ReplyAdd", method=RequestMethod.POST)
	public @ResponseBody int board_replyAdd(HttpServletRequest request) throws JsonProcessingException {
		System.out.println("Cont - 받은 데이터"+request.getParameterMap());
		Map<String, Object> reqData = (Map<String, Object>) request.getParameterMap();
		HashMap<String, Object> resData = new HashMap<String, Object>();
		System.out.println("reqData : "+reqData);
		for(String key : reqData.keySet()) {
			System.out.println("key : " + key );
			System.out.println("value  : " +((String[])reqData.get(key))[0]);
			resData.put(key, ((String[])reqData.get(key))[0]);
		}
		System.out.println("resData 값 출력");
		for(String key : resData.keySet()) {
			System.out.println("key : " + key +" /  value : "+resData.get(key));
		}
		int result = bservice.board_ReplyAdd(resData);
		System.out.println("댓글 등록 결과 값 : " + result);
		
		return result;
		
	}
	
	// 게시물 리스트
	@RequestMapping(value="board/boardList", method=RequestMethod.GET)
	public @ResponseBody ArrayList<Object> boardList(HttpServletRequest request){
		ArrayList<Object> boardList = bservice.getboardList();
		
		System.out.println("boardlist cont"+boardList);
		return boardList;
	}
	
	
	// 게시물 좋아요 버튼 클릭했을때
	@RequestMapping(value="board/boardLikes", method=RequestMethod.GET)
	public @ResponseBody Board boardLikes(HttpServletRequest request) {
		System.out.println("======== 파라미터 =======cnt :"+request.getParameter("cnt"));
		System.out.println("======== 파라미터 =======boardkey : "+request.getParameter("boardkey"));
		System.out.println("======== 파라미터 =======useremail : "+request.getParameter("user_email"));
		String cnt = request.getParameter("cnt");
		String user_email = request.getParameter("user_email");
		int boardkey = Integer.parseInt(request.getParameter("boardkey"));
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("cnt", cnt);
		reqMap.put("boardkey", boardkey);
		reqMap.put("user_email", user_email);
		Board board = bservice.boardLikes(reqMap);
		return board;
	}
	
	// 게시물 싫어요 버튼 클릭했을때
		@RequestMapping(value="board/boardDisLikes", method=RequestMethod.GET)
		public @ResponseBody Board boardDisLikes(HttpServletRequest request) {
			System.out.println("======== 파라미터 =======cnt :"+request.getParameter("cnt"));
			System.out.println("======== 파라미터 =======boardkey : "+request.getParameter("boardkey"));
			System.out.println("======== 파라미터 =======useremail : "+request.getParameter("user_email"));
			String cnt = request.getParameter("cnt");
			String user_email = request.getParameter("user_email");
			int boardkey = Integer.parseInt(request.getParameter("boardkey"));
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("cnt", cnt);
			reqMap.put("boardkey", boardkey);
			reqMap.put("user_email", user_email);
			Board board = bservice.boardDisLikes(reqMap);
			System.out.println("boardDisLikes" + board) ;
			return board;
		}
		
		
		// 대댓글 추가
		@RequestMapping(value="board/board_reReplyAdd", method=RequestMethod.POST)
		public @ResponseBody int board_reReplyAdd(HttpServletRequest request) {
			System.out.println("대댓글 추가");
			Map<String, Object> paraMap = (Map<String, Object>) request.getParameterMap();	
			HashMap<String, Object> reqMap = new HashMap<String, Object>();
			System.out.println("============================= reqMap============================");
			for(String key : paraMap.keySet()) {
				System.out.println("key : " + key );
				System.out.println("value  : " +((String[])paraMap.get(key))[0]);
				reqMap.put(key, ((String[])paraMap.get(key))[0]);
			}
			System.out.println("resData 값 출력");
			for(String key : reqMap.keySet()) {
				System.out.println("key : " + key +" /  value : "+reqMap.get(key));
			}
			
			
			
			
			int result = bservice.board_reReplyAdd(reqMap);
			System.out.println("=================== reReplyAdd result : " + result);
			return result;
			
		}
		
		// 영상 전체 리스트
		@RequestMapping(value="board/board_DataList", method=RequestMethod.GET)
		public @ResponseBody ArrayList<Object> board_DataList(HttpServletRequest request){
			
			ArrayList<Object> DataList = bservice.boardDataList();
			System.out.println("전체 출력 : "+DataList);
			return DataList;
		}
		
		
		//댓글의 최대 level 값
		@RequestMapping(value="board/board_maxRelevel", method=RequestMethod.GET)
		public @ResponseBody int board_maxRelevel(HttpServletRequest request) {
			int board_idx = Integer.parseInt(request.getParameter("board_idx").toString());
			int result = bservice.board_maxRelevel(board_idx);
			System.out.println("ajax result  결과값  : " + result);
			return 0;
		}
		
		
		@RequestMapping(value="board/board_SearchData", method=RequestMethod.GET)
		public @ResponseBody ArrayList<Object> board_SearchData(HttpServletRequest request){
			System.out.println("============== 검색어 "+request.getParameter("inputData").toString());
			String reqParam = request.getParameter("inputData").toString();
			ArrayList<Object> result = bservice.board_SearchData(reqParam);
			return result;
		}
}

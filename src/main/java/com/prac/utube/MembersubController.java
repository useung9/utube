package com.prac.utube;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prac.utube.service.MembersubService;
import com.prac.utube.vo.Membersub;



@Controller
public class MembersubController {

	@Autowired
	private MembersubService msubservice;
	private static final Logger log = LoggerFactory.getLogger(MembersubController.class);
	
	
	// 게시판 데이터 계정별 구독 리스트 
	@RequestMapping(value="/membersub/sublist", method = RequestMethod.GET)
	public String sublist(HttpServletRequest request, HttpSession session, Model model  ) {
		System.out.println("세션 아이디 값 확인 " + session.getAttribute("user_email"));
		if(session.getAttribute("user_email") != null) {
		String user_email = session.getAttribute("user_email").toString();
		
		System.out.println("로그인 계정별 구독 리스트 불러오기 아이디 : " + user_email );
		List<Membersub> sublist =  msubservice.sublist(user_email);
		System.out.println("구독리스트 : "+sublist);
		model.addAttribute("sublist", sublist);
		}
		return "side";
	}
	
	
}

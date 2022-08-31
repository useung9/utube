package com.prac.utube;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prac.utube.service.MembersubService;
import com.prac.utube.vo.Membersub;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	

	
	@Autowired
	private MembersubService msubservice;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	// 기본홈화면
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
		
		return "home";
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		
		
		return "member/login";
	}
	
	// 가입
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model) {
	
		
		return "member/signup";
	}
	
	
	
	// side 로그인시 계정 구독 리스트 출력
	
	@RequestMapping(value="/sublist", method = RequestMethod.GET)
	public @ResponseBody List<Membersub> getsublist(HttpServletRequest request, HttpSession session, Model model  ) {
		
		String reqValue = request.getParameter("user_email");
		System.out.println("ajax value : "+reqValue);
		if(session.getAttribute("user_email") != null) {
		String user_email = session.getAttribute("user_email").toString();
		
		System.out.println("로그인 계정별 구독 리스트 불러오기 아이디 : " + user_email );
		List<Membersub> sublist =  msubservice.sublist(user_email);
		System.out.println("구독리스트 : "+sublist);
		model.addAttribute("sublist", sublist);
		return sublist;
		}
		return null;
	}
	
}

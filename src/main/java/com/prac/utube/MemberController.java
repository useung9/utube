package com.prac.utube;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prac.utube.service.MemberServiceImpl;
import com.prac.utube.service.MembersubServiceImpl;
import com.prac.utube.vo.Member;
import com.prac.utube.vo.Membersub;

@PropertySource("classpath:application.properties")
@Controller
public class MemberController {

	@Autowired
	private MemberServiceImpl mService;
	
	@Autowired
	private MembersubServiceImpl msubService;
	
	@Value ("{file.savedir}")
	private String dir;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	

	// 가입시 세션 + 메인 홈 이동
	@RequestMapping(value = "/member/signUp", method = RequestMethod.POST)
	public String signup(Model model, HttpServletRequest request , @RequestParam("file") MultipartFile multi, @ModelAttribute("Member") Member member, HttpSession session, RedirectAttributes rattr) {
		// 유효성 검사후 컨트롤러 데이터 전송확인
		System.out.println("membercont");
		System.out.println(member);
		
		
		  log.info("파일명 : " + multi.getOriginalFilename()); 
		  log.info("파일크기 : " + multi.getSize());
		  member.setUser_img(multi.getOriginalFilename());
		  
		  // uploadFolder\\gongu03.jpg으로 조립 // 이렇게 업로드 하겠다라고 설계 // 이미지 파일 VO에 저장
		  //board.setBoard_thum(multi.getOriginalFilename());
		 
		
		// 해당 이메일 중복 여부 확인
		Member ExistsMem = mService.userExists(member.getUser_email());
		System.out.println("컨트롤러 유무 확인한 VO" + ExistsMem);
	
		if(ExistsMem == null) {
			// 등록
			mService.MemberJoin(member,multi);
			rattr.addFlashAttribute("msg", "등록성공");
		return "redirect:/";
		}
		
		rattr.addFlashAttribute("msg", "등록실패");
		return "redirect:/signup";
		
	}
	
	// 로그아웃
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request , HttpSession session) {
		// 로그아웃시 세션 초기화
		session.invalidate();

		return "redirect:/";
	}
	// 아이디값 db있는지 검사
	@RequestMapping(value="/member/login", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object>  idChcek(HttpServletRequest request, HttpSession session, Model model){
		
		// json 받기
		String user_email = request.getParameter("user_email").toString();
		String user_password = request.getParameter("user_password").toString();
		
		System.out.println(user_email + "/ /" +user_password);
		Map<String, Object> result = mService.login(user_email, user_password);
		System.out.println(result);
		System.out.println("result info : ");
		for(String key : result.keySet()) {
			System.out.println("result.get(key) : " + result.get(key));
		}
		
		Member member = mService.selectuser(user_email);
		System.out.println("Member : 가져온계정 정보 " + member);
		if(member != null) {
		// 스크립트에서 세션 사용을 위한 모델
		model.addAttribute("user_email", user_email);
		
		session.setAttribute("user_email", member.getUser_email());
		session.setAttribute("user_name", member.getUser_name());
		session.setAttribute("user_auth", member.getUser_auth());
		session.setAttribute("user_img", member.getUser_img());
		session.setMaxInactiveInterval(3600);
		}
		System.out.println("result 값 출력");
		for(String key : result.keySet()) {
			System.out.println(" result : " + key+" / "+ result.get(key));
		}
		return result;
	}
	
	// 구독추가
	@RequestMapping(value="member/addsub", method=RequestMethod.GET)
	public String addsub(HttpServletRequest request) {
		
		
		return "member/addsub";
	}
	
	// 이미지 페이지명 url 입력 데이터 저장
	@RequestMapping(value="member/addsub", method = RequestMethod.POST)
	public String addsubpost(HttpServletRequest request, @RequestParam("file") MultipartFile multi, @ModelAttribute("Membersub") Membersub membersub ) {
		System.out.println("dir 업로드 경로 --------- "+dir);
		
		System.out.println("multipart --------- "+multi);
		
			log.info("파일명 : " + multi.getOriginalFilename());
			log.info("파일크기 : " + multi.getSize());
			
			// uploadFolder\\gongu03.jpg으로 조립
			// 이렇게 업로드 하겠다라고 설계
		System.out.println("membersub dao " + membersub);
		// 파일명 담기
		membersub.setSub_subimg(multi.getOriginalFilename());
		msubService.addSub(membersub,multi);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="member/detail", method=RequestMethod.GET)
	public String detail(HttpServletRequest request, @RequestParam(required = false) String email, Model model, HttpSession session) {
		// 사용자 정보.
		// 자체가없을때 .
		System.out.println("required false check = 없으면 null 값 체크된다. "
				+ "파라미터 email의 값이 null인 경우 : 본인정보 체크하기"
				+ "파라미터 값이 null이 아닌 경우 관리자가 사용자 정보 체크"+email);
		System.out.println("상세정보 유저 " + session.getAttribute("user_email"));
		
		if(session.getAttribute("user_email").toString() != null) {
			String detailuser = session.getAttribute("user_email").toString();
			Member member;
			if(email == null) {
				System.out.println("email null 값");
				member = mService.selectuser(detailuser);			
			}else{
				System.out.println("email null 아닌경우 " );
				member = mService.selectuser(email);
			}
			System.out.println("detail info : " + member);
			model.addAttribute("userinfo",member);
			return "member/memberDetail";
		}
		// 유저 정보 가져오기
		// 파라미터  email의 값이 null인경우 접속 정보에대한 정보. 
		// false 파라미터이메일에 맞는 데이터
return null;
	}
	
	
	// 사용자 계정 업데이트
	@RequestMapping(value="member/memberUpdate" , method=RequestMethod.POST)
	public String memberUpdate(HttpServletRequest request ,@RequestParam("file") MultipartFile multi, @ModelAttribute("Member") Member member) {
		System.out.println(member);
		log.info("multi : " + multi);
		log.info("파일명 : " + multi.getOriginalFilename());
		log.info("파일크기 : " + multi.getSize());
		
		int result = mService.memberUpdate(member, multi);
		
			return "redirect:/";
		
	}
	
	// 가입 계정 전체 리스트
	@RequestMapping(value="member/memberList", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Object> memberList(HttpServletRequest request){
		ArrayList<Object> memberList = mService.getMemberList();
		return memberList;
	}
	
	// 회원 탈퇴
	@RequestMapping(value ="member/deleteUser", method=RequestMethod.POST)
	public @ResponseBody String	deleteUser (HttpServletRequest request) {
		log.info("============= deleteUser 회원삭제");
		System.out.println(request.getParameter("user_email"));
		String delUser = request.getParameter("user_email");
		
		int result = mService.deluser(delUser);
		System.out.println("회원삭제 결과"+result);
		
		
		
		return "";
	}
}

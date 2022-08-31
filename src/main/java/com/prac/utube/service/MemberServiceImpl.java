package com.prac.utube.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prac.utube.repository.MemberRepo;
import com.prac.utube.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired // 비밀번호 암호화
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private MemberRepo mRepo;

	// 회원가입
	@Override
	public int MemberJoin(Member member, MultipartFile multi) {

		System.out.println("서비스단 member" + member);
		int result = 0;
		// 비밀번호 암호화
		String cryptPasswd;

		cryptPasswd = bCryptPasswordEncoder.encode(member.getUser_password());

		member.setUser_password(cryptPasswd);
		System.out.println("pw");

		// db 저장
		System.out.println("서비스단 암호화 member" + member);
		if (member.getUser_img() != "" && member.getUser_img().contains(".")) {
			// 이미지
			// 업로드 패스 정해두기
			String uploadpath = "C:\\Users\\user\\Desktop\\utube\\thum";
			String filename = member.getUser_img();
			String fileExtension = member.getUser_img().substring(filename.lastIndexOf("."), filename.length());

			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");

			String uniqueName = uuids[0];
			System.out.println("생성된 고유문자열" + uniqueName);
			System.out.println("확장자명" + fileExtension);

			File saveFile = new File(uploadpath + "\\" + filename); // 적용 후
			// 경로 오류남 . membersub.setSub_subimg(uploadpath+"\\"+filename);
			try {
				multi.transferTo(saveFile);
				System.out.println("업로드성공 경로 : " + uploadpath);
				result = mRepo.memberInsert(member);
				System.out.println(result == 1 ? "정상적 등록 완료" : "등록에 실패 했습니다.");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		result = mRepo.memberInsert(member);
		return result;
	}

	// 로그인
	@Override
	public Map<String, Object> login(String user_email, String user_password) {
		Map<String, Object> result = new HashMap<String, Object>();

		// 1 로그인 정보가져오기

		// 2 db에서 아이디값 조회 있으면 진행 없으면 결과리턴 분기
		Member member = mRepo.memberSelect(user_email);
		System.out.println("파라미터 : user_email : "+ user_email +"\n"+"member : "+ member);
		if (member != null) {
			System.out.println("member.getUser_email().equals(\"\")" + member.getUser_email().equals(""));
			System.out.println(" member.getUser_email() != null" + member.getUser_email() != null);

			System.out.println("파라미터 비밀번호 " + user_password);
			System.out.println("bCryptPasswordEncoder.matches(user_password, member.getUser_password()) ==>"
					+ bCryptPasswordEncoder.matches(user_password, member.getUser_password()));
			if (!member.getUser_email().equals("") && member.getUser_email() != null) {
				// System.out.println("member.getUser_email().equals(user_email)" +
				// member.getUser_email().equals(user_email));
				if (bCryptPasswordEncoder.matches(user_password, member.getUser_password())) {
					// 로그인 성공
					System.out.println("로그인 성공");
					result.put("code", 1);
					result.put("msg", "로그인 성공");
					result.put("name", member.getUser_name());
					// 세션 추가
					System.out.println("put 추가" + result.get("code"));

				} else {
					System.out.println("비밀번호 해시값 비교  정보가 일치하지않음");
				}
//					System.out.println("아이디가 일치하지않음");
//					result.put("code",2);
//					result.put("msg","아이디가 일치하지않음.");
//					
//				return result;
//			} 
			} else {
				System.out.println("아이디가 일치하지않음");
				result.put("code", 2);
				result.put("msg", "아이디가 일치하지않음.");
			}
			
		}
		System.out.println("해당정보없음");
		result.put("code", 2);
		result.put("msg", "해당정보없음");

		System.out.println("서비스단  + " + result);
		return result;

	}

	// 이메일 등록전 유무확인
	@Override
	public Member userExists(String user_email) {
		Member member = mRepo.memberSelect(user_email);
		System.out.println("Memberservice - Member value : " + member);
		return member;
	}

	// 이메일 중복 여부
	@Override
	public Member selectuser(String user_email) {
		Member member = mRepo.memberSelect(user_email);
		return member;
	}

	// 회원수정
	@Override
	public int memberUpdate(Member member, MultipartFile multi) {
		System.out.println("회원정보 수정 memeber : " + member);
		System.out.println("비밀번호 " + multi.getOriginalFilename());
		String cryptPasswd;
		int result = 0;
		if (member.getUser_password() != null) {
			cryptPasswd = bCryptPasswordEncoder.encode(member.getUser_password());
			member.setUser_password(cryptPasswd);
		}

		// db 저장
		System.out.println("서비스단 암호화 member" + member);
		// 이미지 데이터가 있다면
		if (!(multi.getOriginalFilename().equals("")) && multi.getOriginalFilename() != null) {
			member.setUser_img(multi.getOriginalFilename());
			// 이미지
			// 업로드 패스 정해두기
			String uploadpath = "C:\\Users\\user\\Desktop\\utube\\thum";
			String filename = member.getUser_img();
			String fileExtension = member.getUser_img().substring(filename.lastIndexOf("."), filename.length());

			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String[] uuids = uuid.toString().split("-");

			String uniqueName = uuids[0];
			System.out.println("생성된 고유문자열" + uniqueName);
			System.out.println("확장자명" + fileExtension);

			File saveFile = new File(uploadpath + "\\" + filename); // 적용 후
			// 경로 오류남 . membersub.setSub_subimg(uploadpath+"\\"+filename);
			try {
				multi.transferTo(saveFile);
				System.out.println("업로드성공 경로 : " + uploadpath);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		result = mRepo.memberUpdate(member);
		System.out.println(result == 1 ? "정상적 등록 완료" : "등록에 실패 했습니다.");
		return result;
	}

	// 사용자 전체목록
	@Override
	public ArrayList<Object> getMemberList() {

		return mRepo.getMemberList();
	}

	// 회원삭제 1건
	@Override
	public int deluser(String delUser) {

		return mRepo.delUser(delUser);
	}

}

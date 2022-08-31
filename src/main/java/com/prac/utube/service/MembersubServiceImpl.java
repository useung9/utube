package com.prac.utube.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prac.utube.repository.MembersubRepo;
import com.prac.utube.vo.Membersub;

@Service
public class MembersubServiceImpl implements MembersubService{

	@Autowired
	private MembersubRepo msubrepo;
	
	
	// 로그인된 계정의 구독 정보리스트 읽어오기
	@Override
	public List<Membersub> sublist(String user_email) {
		
		return msubrepo.sublist(user_email);
	}


	@Override
	public int addSub(Membersub membersub, MultipartFile multi) {
			
		// 업로드 패스 정해두기
		String uploadpath = "C:\\Users\\user\\Desktop\\utube\\upload";
		String filename = membersub.getSub_subimg();
		String fileExtension = membersub.getSub_subimg().substring(filename.lastIndexOf("."), filename.length());
		
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		
		String uniqueName = uuids[0];
		System.out.println("생성된 고유문자열" + uniqueName);
		System.out.println("확장자명" + fileExtension);
		
		
		File saveFile = new File(uploadpath+"\\"+filename);  // 적용 후
		//  경로 오류남 . membersub.setSub_subimg(uploadpath+"\\"+filename);
		try {
			multi.transferTo(saveFile);
			System.out.println("업로드성공 경로 : "+uploadpath);
			int result = msubrepo.addSub(membersub);
			System.out.println(result == 1?"정상적 등록 완료":"등록에 실패 했습니다.");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	

}

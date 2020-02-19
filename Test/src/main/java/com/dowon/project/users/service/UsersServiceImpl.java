package com.dowon.project.users.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dowon.project.users.dao.UsersDao;
import com.dowon.project.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao dao;
	//인자로 전달된 아이디가 존재하는지 여부를 Map 에 담아서 리턴하는 메서드
	@Override
	public Map<String, Object> isExistId(String inputId) {
		boolean isExist=dao.isExist(inputId);
		Map<String, Object> map=new HashMap<>();
		map.put("isExist", isExist);	//=> {"isExist":true} or {"isExist":false} 이런식으로 응답 됨. 따로 jsp페이지를 만들지 않고도.
		return map;
	}
	@Override
	public void addUser(UsersDto dto) {
		//비밀전호를 암호화 한다.
		String encodedPwd=new BCryptPasswordEncoder().encode(dto.getPwd());		//@Autowired를 해서 객체를 하나만 생성해서 사용하면 더 나은 구조가 된다.
		//암호화된 비밀번호를 UsersDto에 다시 넣어준다.
		dto.setPwd(encodedPwd);
		//UsersDao 객체를 이용해서 DB에 저장하기
		dao.insert(dto);
	}
	
	@Override
	public void validUser(UsersDto dto, HttpSession session, ModelAndView mView, HttpServletRequest request) {
		//아이디 비밀번호가 유효한지 여부
		boolean isValid=false;
		//아이디를 이용해서 저장된 비밀번호를 읽어온다.
		String pwdHash=dao.getPwdHash(dto.getId());
		if(pwdHash != null) {//비밀번호가 존재하고
			//입력한 비밀번호와 일치한다면 로그인 성공.
			isValid=BCrypt.checkpw(dto.getPwd(), pwdHash);
		}
		if(isValid) {
			//로그인 처리를 한다.
			session.setAttribute("id", dto.getId());
			String profile=dao.getProfile(dto.getId());
			session.setAttribute("profile", profile);
			String ip=request.getRemoteAddr();
			session.setAttribute("ip", ip);
			System.out.println("접속 정보: "+dto.getId()+" | "+ip);
		}
	}
	@Override
	public void showInfo(String id, ModelAndView mView) {
		UsersDto dto=dao.getData(id);
		mView.addObject("dto",dto);
	}
	@Override
	public String saveProfileImage(HttpServletRequest request, MultipartFile mFile) {
		//파일을 저장할 폴더의 절대 경로를 얻어온다.
		String realPath=request.getServletContext().getRealPath("/upload");
		//원본 파일명
		String orgFileName=mFile.getOriginalFilename();
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File file=new File(filePath);
		if(!file.exists()){//디렉토리가 존재하지 않는다면
			file.mkdir();//디렉토리를 만든다.
		}
		//파일 시스템에 저장할 파일명을 만든다. (겹치치 않게)
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try{
			//upload 폴더에 파일을 저장한다.
			mFile.transferTo(new File(filePath+saveFileName));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//UsersDao 객체를 이용해서 프로파일 이미지
		//경로를 DB 에 저장하기
		String path="/upload/"+saveFileName;	//ajax 요청에 대한 응답으로 보낼 경로.		
		//로그인된 아이디
		String id=(String)
				request.getSession().getAttribute("id");
		//아이디와 프로파일 이미지 경로를 dto 에 담고 
		UsersDto dto=new UsersDto();
		dto.setId(id);
		dto.setProfile(path);
		// UsersDao 를 이용해서 DB 에 반영하기 
		dao.updateProfile(dto);
		request.getSession().setAttribute("profile", dto.getProfile());
		//이미지 경로 리턴해주기 
		return path;
	}
	
	@Override
	public void updatePassword(UsersDto dto, ModelAndView mView) {
		//1. 예전 비밀번호가 맞는 정보인지 확인
		String pwdHash=dao.getData(dto.getId()).getPwd();	//저장된 암호 읽어오기
		boolean isValid=BCrypt.checkpw(dto.getPwd(), pwdHash);	//dto.getPwd()는 입력한 예전 비밀번호, pwdHash는 암호화 되어 저장된 비밀번호. 둘을 비교함.
		//2. 만일 맞다면 새로 비밀번호를 암호화 해서 저장하기
		if(isValid) {
			//새 비밀번호를 암호화해서 dto 에 담고
			String encodedPwd=new BCryptPasswordEncoder().encode(dto.getNewPwd());
			dto.setPwd(encodedPwd);	
			//DB 에 수정 반영하기
			dao.updatePwd(dto);
			mView.addObject("isSuccess",true);
		}else {
			mView.addObject("isSuccess",false);			
		}
	}
	@Override
	public void updateUser(UsersDto dto) {
		dao.updateUser(dto);
	}
	@Override
	public void deleteUser(String id) {
		dao.delete(id);
	}
}

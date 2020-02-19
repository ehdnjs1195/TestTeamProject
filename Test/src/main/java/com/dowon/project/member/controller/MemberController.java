package com.dowon.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dowon.project.member.dto.MemberDto;
import com.dowon.project.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	// 회원 목록 보기 요청(/member/list.do) 을 처리 할 컨트롤러의 메소드
	@RequestMapping("/member/list") // .do 생략가능
	public ModelAndView list(ModelAndView mView) {
		//MemberServiceImpl 객체를 이용해서 비즈니스 로직 처리
		service.getList(mView);
		// 어디로 포워드 이동 할 것인지 뷰페이지 정보를 리턴해준다. (view page 정보)
		mView.setViewName("member/list"); //  뷰페이지 정보가 여기 있다고 알려주는 것 /WEB_INF/view/member/list.jsp
		return mView; //Model 과 view page 정보가 담긴 객체를 리턴해준다.
	}
	
	//회원정보 삭제 요청 처리
	@RequestMapping("/member/delete")
	public String delete(@RequestParam int num) { // @ReuqestParam 어노테이션은 HttpServletRequest 객체와 같은 역할을 한다.[request.Parameter]
		//MemberServiceImpl 객체를 이용해서 회원정보 삭제
		service.deleteMember(num);
		//리다일렉트 응답
		return "redirect:/member/list.do"; //redirect 므로 삭제되면 화면이 다시 요청된다.
	}
	
	@RequestMapping("/member/insertform") // / 빠트리면 안됨!
	public String insertform() {
		//수행할 비즈니스 로직은 없다.
		return "member/insertform"; // / 쓰면 안됨!
	}
	
	/*
	 *  @ModelAttribute MemberDto dto 를 메소드의 인자로 선언하면
	 *  폼 전송되는 파라미터가 자동으로 MemberDto 객체에 setter 메소드를 통해서
	 *  들어가고 그 객체가 메소드의 인자로 전달된다.
	 *  단, 파라미터명과 Dto 의 필드명이 일치해야 된다. 
	 */
	@RequestMapping("/member/insert")
	public ModelAndView insert(@ModelAttribute("dto") MemberDto dto, ModelAndView mView) { // 자동 추출되서 전달된다.
		//서비스를 통해서 비즈니스 로직 처리
		service.addMember(dto);
		/*
		 *  @ModelAttribute("dto") MemberDto dto 의 의미는
		 *  1. 전송되는 파라미터를 자동으로 추출해서 MemberDto 에 담아 주기도 하고
		 *  2. "dto" 라는 키값으로  MemberDto 객체를 request 영역에 담아주는 역활도 한다.
		 * 
		 */
		//mView.addObject("dto", dto); => ModelAndView에 담아서 뷰페이지에 전달하기
		mView.setViewName("member/insert");
		return mView;
	}
	
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam int num, ModelAndView mView) {
		//ModelAndView 객체에 회원정보가 담기도록 서비스의 메소드 호출
		service.getMember(mView, num);
		// view page 로 forward 이동해서 수정할 회원의 정보를 출력해 준다.
		mView.setViewName("member/updateform");
		return mView;
	}
	@RequestMapping("/member/update")
	public ModelAndView update(@ModelAttribute("dto") MemberDto dto, ModelAndView mView) { //수정할 정보가 dto에 자동으로 담긴다.
		//회원정보가 수정 되도록 서비스의 메소드 호출
		service.updateMember(dto);
		mView.setViewName("member/update");
		return mView;
	}
}

package com.dowon.project.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.dowon.project.cafe.dto.CafeDto;

public interface CafeService {
	public void list(HttpServletRequest request);
	public void addContent(CafeDto dto,HttpServletRequest request);
	public void getContent(ModelAndView mView, int num);
	//게시글을 삭제하는 메소드
	public void deleteContent(int num,HttpServletRequest request);
	//댓글을 삭제하는 메소드

	//원글 수정하는 메소드
	public void updateContent(CafeDto dto);
	public void getDetail(HttpServletRequest request);
	

}

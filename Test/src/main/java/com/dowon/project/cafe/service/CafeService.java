package com.dowon.project.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface CafeService {
	public void getList(HttpServletRequest request);
}

package com.dowon.project.member.dao;

import java.util.List;

import com.dowon.project.member.dto.MemberDto;

public interface MemberDao {
	public List<MemberDto> getList();
	
	public void delete(int num);
	
	public void insert(MemberDto dto);
	
	public MemberDto getData(int num);
	
	public void update(MemberDto dto);
}

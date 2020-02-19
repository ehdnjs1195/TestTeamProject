package com.dowon.project.cafe.dao;

import java.util.List;

import com.dowon.project.cafe.dto.CafeDto;

public interface CafeDao {
	public int getCount(CafeDto dto);//글의 갯수
	public List<CafeDto> getList(CafeDto dto);//글 목록
	public void insert(CafeDto dto);//글 추가
	public CafeDto getDate(CafeDto dto);//검색된 글정보의 정보
	public CafeDto getData(int num);//글 하나의 정보
	public void delete(int num);//그 삭제
	public void update(CafeDto dto);//글 정보 수정
	public void addViewCount(int num);//조회수 증가
	public void commUpdate(CafeDto dto);//댓글 추가
}

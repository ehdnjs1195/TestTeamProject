package com.dowon.project.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dowon.project.cafe.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	@Autowired
	private SqlSession session;
	
	@Override
	public int getCount(CafeDto dto) {
		return session.selectOne("cafe.getCount",dto);
	}

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		List<CafeDto> list= session.selectList("cafe.getList",dto);
		return list;
	}

	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert",dto);		
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete",num);
	}

	@Override
	public CafeDto getData(int num) {
		CafeDto dto=session.selectOne("cafe.getData2",num);
		return dto;
		
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update",dto);
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafe.addViewCount",num);
	}

	@Override
	public void commUpdate(CafeDto dto) {
		session.update("cafe.commupdate",dto);	
		
	}

	@Override
	public CafeDto getDate(CafeDto dto) {
		return session.selectOne("cafe.getData1", dto);
	}

	
}

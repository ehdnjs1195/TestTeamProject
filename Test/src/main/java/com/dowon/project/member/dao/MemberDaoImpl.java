package com.dowon.project.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dowon.project.member.dto.MemberDto;

// dao 는 Repository 어노테이션을 써야 bean이 된다. => dao 도 스프링에 맡기는 것 => @Repository 를 적어줘서 스프링빈 컨테이너에서 관리된다.
@Repository
public class MemberDaoImpl implements MemberDao{

	//핵심 의존 객체를 spring 으로 부터 주입 받기 (Dependency Injection)[DI]
	@Autowired
	private SqlSession session; //field 선언만 하면 null 이지만 Autowired 어노테이션을 적어주면 Spring bean container 로부터 값이 알아서 들어간다.
	
	@Override
	public List<MemberDto> getList() {
		// spring bean container 로 부터 참조값을 부여 받는다.
		List<MemberDto> list=session.selectList("member.getList"); //MemberMapper 에 select 문을 만들어 놓고 참조만 해주면 바로 사용 할 수 있다. ("namespace.select문 id")

		return list;
	}

	@Override
	public void delete(int num) {
		session.delete("member.delete", num); //ParameterType 은 int 인 것을 기억하고 MemberMapper 로 이동해서 작성해준다. 
		// session.delete("member.delete", 여기는 오브젝트 타입으로 받을 수 있어서 어떤 타입이든 받을 수 있다.)
	}

	@Override
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto); // ("namespace.sql문 id", dto)
		
	}

	@Override
	public MemberDto getData(int num) { //parameterType => int
		// row가 한개 이므로 selectOne() 메소드를 사용하고 selectOne() 메소드를 호출해서 select 하면 resultType 이 메소드의 리턴 type 이 된다.
		MemberDto dto=session.selectOne("member.getData", num); //resultType => MemberDto (칼럼이 3개여서 dto에 넣은 것)
		
		return dto;
	}

	@Override
	public void update(MemberDto dto) { //parameterType = MemberDto
		session.update("member.update", dto);
		
	}

}

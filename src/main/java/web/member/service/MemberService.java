package web.member.service;

import java.util.List;

import web.member.pojo.Member;

public interface MemberService {

	Member register(Member member);

	Member login(Member member);
	
	Member edit(Member member);
	
	List<Member> findAll();
	
	boolean remove(Integer id);
	
	boolean save(Member member);
}
package web.member.dao;

import core.dao.CoreDao;
import web.member.pojo.Member;

public interface MemberDao extends CoreDao<Member, Integer> {
	
	Member selectByUsername(String username);
	
	Member selectForLogin(String username, String password);
}

package web.member.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.member.util.MemberConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.member.pojo.Member;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Member member = json2Pojo(request, Member.class);
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
			writePojo2Json(response, member);
			return;
		}
		
		member = SERVICE.login(member);
		if (member.isSuccessful()) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("loggedin", true);
			session.setAttribute("member", member);
		}
		writePojo2Json(response, member);
	}
}

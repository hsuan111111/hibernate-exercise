package web.member.controller;

import static core.util.CommonUtil.writePojo2Json;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.member.pojo.Member;

@WebServlet("/member/getInfo")
public class GetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Member member = (Member) request.getSession().getAttribute("member");
		if (member == null) {
			member = new Member();
			member.setMessage("無會員資訊");
			member.setSuccessful(false);
		} else {
			member.setSuccessful(true);
		}
		writePojo2Json(response, member);
	}
}

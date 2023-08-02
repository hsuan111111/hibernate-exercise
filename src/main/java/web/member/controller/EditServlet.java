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

@WebServlet("/member/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		final HttpSession session = request.getSession();
		final String username = ((Member) session.getAttribute("member")).getUsername();
		Member member = json2Pojo(request, Member.class);
		member.setUsername(username);
		writePojo2Json(response, SERVICE.edit(member));
	}
}

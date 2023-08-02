package web.member.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;
import static web.member.util.MemberConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.pojo.Core;
import web.member.pojo.Member;

@WebServlet("/member/save")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		final Member member = json2Pojo(request, Member.class);
		final Core core = new Core();
		if (member == null) {
			core.setMessage("無會員資訊");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(SERVICE.save(member));
		}
		writePojo2Json(response, core);
	}
}

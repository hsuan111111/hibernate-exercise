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

@WebServlet("/member/remove")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		final Integer id = json2Pojo(request, Member.class).getId();
		final Core core = new Core();
		if (id == null) {
			core.setMessage("無id");
			core.setSuccessful(false);
		} else {
			core.setSuccessful(SERVICE.remove(id));
		}
		writePojo2Json(response, core);
	}
}

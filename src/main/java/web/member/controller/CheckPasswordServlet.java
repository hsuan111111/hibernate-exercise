package web.member.controller;

import static core.util.CommonUtil.json2Pojo;
import static core.util.CommonUtil.writePojo2Json;

import java.util.Objects;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.pojo.Core;
import web.member.pojo.Member;

@WebServlet("/member/checkPassword")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		final String password = json2Pojo(request, Member.class).getPassword();
		final Member member = (Member) request.getSession().getAttribute("member");
		final Core core = new Core();
		if (member == null) {
			core.setMessage("無會員資訊");
			core.setSuccessful(false);
		} else {
			final String currentPassword = member.getPassword();
			if (Objects.equals(password, currentPassword)) {
				core.setSuccessful(true);
			} else {
				core.setMessage("舊密碼錯誤");
				core.setSuccessful(false);
			}
		}
		writePojo2Json(response, core);
	}
}

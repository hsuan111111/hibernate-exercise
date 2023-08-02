package web.member.controller;

import static core.util.CommonUtil.writePojo2Json;
import static web.member.util.MemberConstants.SERVICE;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/getInfoAll")
public class GetInfoAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		writePojo2Json(response, SERVICE.findAll());
	}
}

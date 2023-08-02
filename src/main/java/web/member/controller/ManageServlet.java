package web.member.controller;

import static core.util.Constants.PREFIX_WEB_INF;
import static web.member.util.MemberConstants.SERVICE;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.member.pojo.Member;

@WebServlet("/member/manage")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> memberList = SERVICE.findAll();
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher(PREFIX_WEB_INF + "/member/manage.jsp").forward(request, response);
	}
}

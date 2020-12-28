package com.sbs.example.jspCommunity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//주소 : /jspCommunity/
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
			
		if (req.getParameter("dan") == null) {
			resp.getWriter().append("dan을 입력해주세요.");
			return;
		}

		int dan = Integer.parseInt(req.getParameter("dan"));
		int limit = 9;

		if (req.getParameter("limit") != null) {
			limit = Integer.parseInt(req.getParameter("limit"));
		}
		
		req.setAttribute("dan",dan);
		req.setAttribute("limit",limit);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/usr/home/index.jsp"); 
		dispatcher.forward(req, resp);
		
	/*	resp.getWriter().append("<h1>" + String.format("구구단 %d단", dan) + "</h1>");

		for (int i = 1; i <= limit; i++) {
			resp.getWriter().append("<div>" + String.format("%d * %d = %d", dan, i, dan * i) + "</div>");
		}*/

	}
}

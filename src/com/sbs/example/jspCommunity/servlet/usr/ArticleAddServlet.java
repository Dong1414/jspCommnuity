package com.sbs.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

@WebServlet("/usr/article/doWrite")
public class ArticleAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		if (req.getParameter("title") == null) {
			resp.getWriter().append("title를 입력해주세요.");
			return;
		}

		String title = (String) req.getParameter("title");
		String body = (String) req.getParameter("body");
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		

		ArticleService articleService = Container.articleService;
		int article = articleService.add(title,body,boardId);
						
		MysqlUtil.closeConnection();

		req.setAttribute("title", title);
		req.setAttribute("body", body);
		req.setAttribute("boardId", boardId);

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/usr/article/doWrite.jsp");
		rd.forward(req, resp);
	}
}

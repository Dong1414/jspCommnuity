package com.sbs.example.jspCommunity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			resp.getWriter().append("제목을 입력하세요");
			return;
		}

		String title = (String) req.getParameter("title");
		String body = (String) req.getParameter("body");

		MysqlUtil.update(new SecSql().append(
				"INSERT INTO article SET regDate = NOW(), updateDate = NOW(), memberId = 1, boardId = 1, title = ?, body = ?",
				title, body));

		req.setAttribute("title", title);
		req.setAttribute("body", body);

		req.getRequestDispatcher("/jsp/usr/article/doWrite.jsp").forward(req, resp);
	}

}

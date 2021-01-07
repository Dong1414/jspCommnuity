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

@WebServlet("/usr/article/detail")
public class ArticleDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		if (req.getParameter("id") == null) {
			resp.getWriter().append("id를 입력해주세요.");
			return;
		}

		int id = Integer.parseInt(req.getParameter("id"));

		Map<String, Object> articleMap = MysqlUtil
				.selectRow(new SecSql().append("SELECT * FROM article WHERE `id` = ?", id));

		MysqlUtil.closeConnection();

		req.setAttribute("articleMap", articleMap);

		req.getRequestDispatcher("/jsp/usr/article/detail.jsp").forward(req, resp);
	}
}
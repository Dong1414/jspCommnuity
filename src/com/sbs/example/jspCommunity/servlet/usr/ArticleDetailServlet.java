package com.sbs.example.jspCommunity.servlet.usr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.service.ArticleService;
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

		int articleId = Integer.parseInt(req.getParameter("id"));
		ArticleService articleService = Container.articleService;
		Map<String, Object> articleMap = articleService.detail(articleId); 
				
		MysqlUtil.closeConnection();

		req.setAttribute("articleMap", articleMap);

		req.getRequestDispatcher("/jsp/usr/article/detail.jsp").forward(req, resp);
	}
}
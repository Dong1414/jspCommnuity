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

@WebServlet("/usr/article/list")
public class ArticleListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");

		if (req.getParameter("boardCode") == null) {
			resp.getWriter().append("boardCode를 입력해주세요.");
			return;
		}		
			String boardCode = "";

			boardCode = (String) req.getParameter("boardCode");

			Map<String, Object> board = MysqlUtil
					.selectRow(new SecSql().append("SELECT * FROM board WHERE `code` = ?", boardCode));

			int id = (int) board.get("id");

			List<Map<String, Object>> articleMapList = MysqlUtil
					.selectRows(new SecSql().append("SELECT * FROM article WHERE boardId = ? ORDER BY id DESC", id));
			MysqlUtil.closeConnection();

			req.setAttribute("articleMapList", articleMapList);
			req.setAttribute("boardMap", board);
			
			req.getRequestDispatcher("/jsp/usr/article/list.jsp").forward(req, resp);
	}
}
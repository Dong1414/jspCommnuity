package com.sbs.example.jspCommunity.servlet.usr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.controller.ArticleController;
import com.sbs.example.jspCommunity.controller.usr.MemberController;
import com.sbs.example.mysqlutil.MysqlUtil;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost");
		doGet(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		if (requestUriBits.length < 4) {
			resp.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}

		String controllerName = requestUriBits[2];
		String actionMethodName = requestUriBits[3];

		MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "jspCommunity");
		
		String jspPath = null;
		
		if (controllerName.equals("member")) {
			MemberController memberController = Container.memberController;

			if (actionMethodName.equals("list")) {
				jspPath = memberController.showList(req, resp);
			}
		} else if (controllerName.equals("article")) {
			ArticleController articleController = Container.articleController;
			
			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(req, resp);
			}
			else if (actionMethodName.equals("detail")) {				
				jspPath = articleController.showDetail(req, resp);
			}
			else if (actionMethodName.equals("delete")) {
				jspPath = articleController.doDelete(req, resp);
			}
			else if (actionMethodName.equals("write")) {
				jspPath = articleController.write(req, resp);
			}
			else if (actionMethodName.equals("doWrite")) {
				jspPath = articleController.doWrite(req, resp);
			}
			else if (actionMethodName.equals("modify")) {
				jspPath = articleController.modify(req, resp);
			}
			else if (actionMethodName.equals("doModify")) {
				jspPath = articleController.doModify(req, resp);
			}
		}

		MysqlUtil.closeConnection();
		System.out.println(jspPath);
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}
}

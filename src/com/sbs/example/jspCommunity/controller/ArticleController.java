package com.sbs.example.jspCommunity.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));

		List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId);

		req.setAttribute("articles", articles);

		return "usr/article/list";
	}

	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
		int articleId = Integer.parseInt(req.getParameter("id"));

		Map<String, Object> article = articleService.getForPrintArticleByArticleId(articleId);		
		
		
		req.setAttribute("article", article);
		
		return "usr/article/detail";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		int articleId = Integer.parseInt(req.getParameter("id"));

		articleService.delete(articleId);
		req.setAttribute("articleId", articleId);

		return "usr/article/delete";
	}

	public String write(HttpServletRequest req, HttpServletResponse resp) {
		String memberId = (String)req.getParameter("memberId");
		String boardId = (String)req.getParameter("boardId");			
		
		req.setAttribute("memberId", memberId);
		req.setAttribute("boardId", boardId);
		
		return "usr/article/write";
	}
	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {		
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		String title = (String)req.getParameter("title");
		String body = (String)req.getParameter("body");								
		
		articleService.add(title, body, boardId);	
		String boardName = articleService.getBoardNameById(boardId);
		
		req.setAttribute("title", title);
		req.setAttribute("body", body);
		req.setAttribute("boardName", boardName);
		
		return "usr/article/doWrite";
	}

	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		String memberId = (String)req.getParameter("memberId");
		String articleId = (String)req.getParameter("id");			
		
		req.setAttribute("memberId", memberId);
		req.setAttribute("articleId", articleId);
		
		return "usr/article/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		String title = (String)req.getParameter("title");
		String body = (String)req.getParameter("body");								
		
		articleService.modify(id, title, body);		
		
		req.setAttribute("title", title);
		req.setAttribute("body", body);
		
		return "usr/article/doModify";
	}


}

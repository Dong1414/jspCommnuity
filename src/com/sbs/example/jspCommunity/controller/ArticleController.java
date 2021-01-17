package com.sbs.example.jspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
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

		Article article = articleService.getForPrintArticleByArticleId(articleId);

		if (article == null) {
			return null;
		}

		req.setAttribute("article", article);

		return "usr/article/detail";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleByArticleId(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		articleService.delete(id);

		int boardId = article.getBoardId();

		req.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=%d", boardId));
		return "common/redirect";
	}

	public String write(HttpServletRequest req, HttpServletResponse resp) {
		String memberId = (String) req.getParameter("memberId");
		String boardId = (String) req.getParameter("boardId");

		req.setAttribute("memberId", memberId);
		req.setAttribute("boardId", boardId);

		return "usr/article/write";
	}

	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		String title = (String) req.getParameter("title");
		String body = (String) req.getParameter("body");

		int articleId = articleService.add(title, body, boardId);
		String boardName = articleService.getBoardNameById(boardId);

		System.out.println(articleId);

		req.setAttribute("title", title);
		req.setAttribute("body", body);
		req.setAttribute("boardName", boardName);

		if (req.getParameter("hashtag").length() > 0) {
			String hashtag = (String) req.getParameter("hashtag");

			articleService.hashAdd(hashtag, articleId);

			String[] hashtagList = (String[]) req.getParameter("hashtag").split(",");
			req.setAttribute("hashtag", hashtagList);
		}
		return "usr/article/doWrite";
	}

	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleByArticleId(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		Board board = articleService.getBoardById(article.getBoardId());

		req.setAttribute("article", article);
		req.setAttribute("board", board);

		return "usr/article/modify";
	}

	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleByArticleId(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		int memberId = Integer.parseInt(req.getParameter("memberId"));

		if (article.getMemberId() != memberId) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 없습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String title = req.getParameter("title");
		String body = req.getParameter("body");

		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("id", id);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);

		articleService.modify(modifyArgs);

		req.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", id));
		return "common/redirect";
	}
}

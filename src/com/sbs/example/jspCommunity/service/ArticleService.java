package com.sbs.example.jspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.container.Container;
import com.sbs.example.jspCommunity.dao.ArticleDao;
import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		return articleDao.getForPrintArticlesByBoardId(boardId);
	}

	public int add(String title, String body,int boardId) {		
		return articleDao.add(title,body,boardId);
	}

	public int delete(int id) {		
		return articleDao.delete(id);
	}

	public Article detail(int articleId) {
		return articleDao.detail(articleId);
	}

	public Article getForPrintArticleByArticleId(int articleId) {
		return articleDao.detail(articleId);
	}
	
	public String getBoardNameById(int boardId) {
		return articleDao.getBoardNameById(boardId);
	}

	public int hashAdd(String hashtag, int articleId) {
		return articleDao.hashAdd(hashtag, articleId);
		
	}

	public void modify(Map<String, Object> args) {
		articleDao.modify(args);
		
	}

	public Board getBoardById(int boardId) {
		
		return articleDao.getBoardById(boardId);
	}



	

}
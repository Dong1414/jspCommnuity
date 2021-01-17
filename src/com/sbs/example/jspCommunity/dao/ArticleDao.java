package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
import com.sbs.example.jspCommunity.dto.Board;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ArticleDao {
	public List<Article> getForPrintArticlesByBoardId(int boardId) {
		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		if (boardId != 0) {
			sql.append("WHERE A.boardId = ?", boardId);
		}
		sql.append("ORDER BY A.id DESC");

		List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> articleMap : articleMapList) {
			articles.add(new Article(articleMap));
		}

		return articles;
	}

	public int add(String title, String body, int boardId) {

		SecSql sql = new SecSql();
		sql.append(
				"INSERT INTO article SET regDate = NOW(), updateDate = NOW(), memberId = 1, boardId = ?, title = ?, `body` = ?",
				boardId, title, body);

		return MysqlUtil.insert(sql);
	}

	public int delete(int id) {

		SecSql sql = new SecSql();
		sql.append("DELETE FROM article WHERE id = ?", id);

		

		return MysqlUtil.update(sql);
	}
	
	public Board getBoardById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT B.*");
		sql.append("FROM board AS B");
		sql.append("WHERE B.id = ?", id);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if ( map.isEmpty() ) {
			return null;
		}

		return new Board(map);
	}

	public int modify(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");

		boolean needToUpdate = false;

		if (args.get("title") != null) {
			needToUpdate = true;
			sql.append(", title = ?", args.get("title"));
		}

		if (args.get("body") != null) {
			needToUpdate = true;
			sql.append(", `body` = ?", args.get("body"));
		}

		if ( needToUpdate == false ) {
			return 0;
		}

		sql.append("WHERE id = ?", args.get("id"));

		return MysqlUtil.update(sql);
	}

	public Article detail(int articleId) {

		SecSql sql = new SecSql();

		sql.append("SELECT A.*");
		sql.append(", M.name AS extra__writer");
		sql.append(", B.name AS extra__boardName");
		sql.append(", B.code AS extra__boardCode");
		sql.append(", H.hash AS extra__hashtag");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("INNER JOIN `board` AS B");
		sql.append("ON A.boardId = B.id");
		sql.append("LEFT OUTER JOIN `hashtag` AS H");
		sql.append("ON A.id = H.articleId");
		sql.append("WHERE A.id = ?", articleId);
		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);
		if(articleMap.isEmpty()) {			
			return null;
		}
		Article article = new Article(articleMap);
		
		return article;
	}

	public String getBoardNameById(int boardId) {
		SecSql sql = new SecSql();

		sql.append("SELECT `name`");		
		sql.append("FROM board");
		sql.append("WHERE id = ?",boardId);
				
		return MysqlUtil.selectRowStringValue(sql);
	}

	public int hashAdd(String hashtag, int articleId) {
		SecSql sql = new SecSql();
		sql.append(
				"INSERT INTO hashtag SET regDate = NOW(), updateDate = NOW(), articleId = ?, hash = ?",articleId,hashtag);

		return MysqlUtil.insert(sql);
		
	}

	
}
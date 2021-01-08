package com.sbs.example.jspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.jspCommunity.dto.Article;
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
		sql.append("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), memberId = 1, boardId = ?, title = ?, `body` = ?",boardId,title,body);		
		
		MysqlUtil.update(sql);

		return 1;
	}

	public int delete(int id) {
		
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article WHERE id = ?",id);
		
		MysqlUtil.update(sql);				
		
		return id;
	}

	public Map<String, Object> modify(int id, String title, String body) {

		SecSql sql = new SecSql();
		sql.append("UPDATE article SET updateDate = NOW(), title = ?, body = ? WHERE id = ?",title, body,id);
		
		MysqlUtil.update(sql);
		
		return null;
	}

	public Map<String, Object> detail(int articleId) {
		
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
		sql.append("WHERE A.id = ?",articleId);
		Map<String, Object> articleMap = MysqlUtil.selectRow(sql);

		return articleMap;
	}
}
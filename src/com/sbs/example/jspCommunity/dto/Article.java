package com.sbs.example.jspCommunity.dto;

import java.util.Arrays;
import java.util.Map;

public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hitsCount;

	private String extra__writer;
	private String extra__boardName;
	private String extra__boardCode;
	private String extra__hashtag;
	
	public int getId() {
		return id;
	}
	public String getRegDate() {
		return regDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public String getTitle() {
		return title;
	}
	public String getBody() {
		return body;
	}
	public int getMemberId() {
		return memberId;
	}
	public int getBoardId() {
		return boardId;
	}
	public int getHitsCount() {
		return hitsCount;
	}
	public String getExtra__writer() {
		return extra__writer;
	}
	public String getExtra__boardName() {
		return extra__boardName;
	}
	public String getExtra__boardCode() {
		return extra__boardCode;
	}
	public String getExtra__hashtag() {
		return extra__hashtag;
	}

	
	
	public Article(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.title = (String) map.get("title");
		this.body = (String) map.get("body");
		this.memberId = (int) map.get("memberId");
		this.boardId = (int) map.get("boardId");
		this.hitsCount = (int) map.get("hitsCount");

		if (map.containsKey("extra__writer")) {
			this.extra__writer = (String) map.get("extra__writer");
		}

		if (map.containsKey("extra__boardName")) {
			this.extra__boardName = (String) map.get("extra__boardName");
		}

		if (map.containsKey("extra__boardCode")) {
			this.extra__boardCode = (String) map.get("extra__boardCode");
		}
	/*	if (map.containsKey("extra__hashtag")) {
			this.extra__hashtag = (String) map.get("extra__hashtag");
		}*/
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + ", hitsCount=" + hitsCount
				+ ", extra__writer=" + extra__writer + ", extra__boardName=" + extra__boardName + ", extra__boardCode="
				+ extra__boardCode + ", extra__hashtag=" + extra__hashtag + "]";
	}

}
package com.sbs.example.jspCommunity.container;

import com.sbs.example.jspCommunity.controller.ArticleController;
import com.sbs.example.jspCommunity.controller.usr.MemberController;
import com.sbs.example.jspCommunity.dao.ArticleDao;

import com.sbs.example.jspCommunity.service.ArticleService;
import com.sbs.example.jspCommunity.dao.MemberDao;
import com.sbs.example.jspCommunity.service.MemberService;

public class Container {
	
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	public static ArticleController articleController;
	public static MemberController memberController;
	
	public static ArticleService articleService;
	public static MemberService memberService;
	

	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
		
		memberController = new MemberController();
		articleController = new ArticleController();
	}
}
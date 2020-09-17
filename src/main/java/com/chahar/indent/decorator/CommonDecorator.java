package com.chahar.indent.decorator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;
import org.displaytag.model.TableModel;

import com.chahar.indent.model.UserMaster;
import com.chahar.indent.util.Constants;

public class CommonDecorator extends TableDecorator {

	private PageContext context;

	private String subContext = Constants.pathString("application.subContext");

	String paginationParam;
	String pagination = "";

	public PageContext getContext() {
		return context;
	}

	public void setContext(PageContext context) {
		this.context = context;
	}

	@Override
	public void init(PageContext pageContext, Object decorated, TableModel tableModel) {
		super.init(pageContext, decorated, tableModel);
		this.setContext(pageContext);

	}

	public String getSrNo() {
		return (getListIndex() + 1) + "";
	}

	public String getContextPath() {
		HttpSession session = getPageContext().getSession();
		ServletContext context = session.getServletContext();
		return context.getContextPath();
	}

	public String getEncryptedPath(String path) {
		return path;
	}

	public String getEnryptedURLEdit(String path) {
		String afterContext = path.substring(path.indexOf(subContext));
		return getContextPath() + afterContext;
	}

	protected String getPageId() {
//		return getPageContext().getRequest().getParameter("pageId");
		return "1204";
	}

	protected UserMaster getUser() {
		UserMaster user = null;
		if (getPageContext().getSession().getAttribute("userMaster") != null) {
			user = (UserMaster) getPageContext().getSession().getAttribute("userMaster");
		}
		return user;
	}

	public String getPagination() {
		paginationParam = getPageContext().getRequest().getParameter("d-49520-p");
		if (StringUtils.isNotBlank(paginationParam)) {
			pagination = "?d-49520-p=" + paginationParam;
		}
		return pagination;
	}

	public String addClassToDelete() {
		return "class=deleteimg";
	}
	/*
	 * protected boolean canEdit(){ UserMaster user = getUser(); if(user != null){
	 * user. } }
	 */
}

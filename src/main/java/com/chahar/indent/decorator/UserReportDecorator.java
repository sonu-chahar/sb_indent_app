package com.chahar.indent.decorator;

public class UserReportDecorator extends CommonDecorator {

//	@SuppressWarnings("unused")
//	private PageContext context;
//
//	public String getActionLink() {
//		UserReportDTO master = (UserReportDTO) getCurrentRowObject();
//		Integer paramId = (Integer) getPageContext().getRequest().getAttribute("id");
//
//		Integer id = master.getId();
//
//		boolean isDeleteDisabled = false;
//		if (id.equals(paramId))
//			isDeleteDisabled = true;
//		String contextPath = getContextPath();
//		String link = "<a id='" + id + "' href='"
//				+ getEnryptedURLEdit(contextPath + "/newSubjectMaster/editNewSubject/" + id + "?pageId=" + getPageId())
//				+ getPagination() + "'><img src='" + getContextPath()
//				+ "/resources/images/Edit.gif' title='Edit'></a> / ";
//		String deleteLink = !isDeleteDisabled
//				? " <a id='" + id + "' href=\"javascript:void(0)\" onclick=\"return deleteObject('Record ?', " + id
//						+ ",'/newSubjectMaster/deleteNewSubject/','" + contextPath + "', " + getPageId() + ")\" "
//						+ addClassToDelete() + "><img src='" + getContextPath()
//						+ "/resources/images/Delete.gif' title='Delete'></a>"
//				: "<img width='15px' height='15px' src='" + getContextPath() + "/resources/images/Block_Icon.png'>";
//		link += deleteLink;
//		return link;
//	}

	/*
	 * public String getManage() { SubjectMaster master = (SubjectMaster)
	 * getCurrentRowObject(); Integer id = master.getId(); String link = "<a id='" +
	 * id + "' href='" + getEnryptedURLEdit(getContextPath() +
	 * "/newSubjectMaster/showPaperDetailsForSubject/" + id + "?pageId=" +
	 * getPageId()) + getPagination() + "'>manage</a>"; return link; }
	 */
}

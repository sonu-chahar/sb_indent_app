package com.chahar.indent.decorator;

import javax.servlet.jsp.PageContext;

import com.chahar.indent.controller.AbstractPageController;
import com.chahar.indent.model.UserMaster;

public class UserMasterDecorator extends CommonDecorator {

	@SuppressWarnings("unused")
	private PageContext context;

	public String getActionLink() {
		UserMaster master = (UserMaster) getCurrentRowObject();
		Long id = master.getId();

		Long paramId = (Long) getPageContext().getRequest().getAttribute(AbstractPageController.REQUEST_ATTRIBUTE_ID);

		boolean isDeleteDisabled = false;
		if (id.equals(paramId))
			isDeleteDisabled = true;
		String contextPath = getContextPath();
		String link = 
				"<a id='" + id 
				+ "' href='" 
				+ contextPath + "/editUser/" 
				+ id + getPagination()
				+ "'><img src='" + getContextPath() 
				+ "/resources/images/Edit.gif' title='Edit'></a> / ";
		String deleteLink = !isDeleteDisabled
				? " <a id='" + id 
						+ "' href=\"javascript:void(0)\" onclick=\"return deleteObject('Record ?', " + id
						+ ",'/deleteUser/','" + contextPath + "')\" " 
						+ addClassToDelete() + "><img src='"
						+ getContextPath() + "/resources/images/Delete.gif' title='Delete'></a>"
				: "<img width='15px' height='15px' src='" + getContextPath() + "/resources/images/Block_Icon.png'>";
		link += deleteLink;
		return link;
	}
}

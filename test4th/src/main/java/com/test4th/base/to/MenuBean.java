package com.test4th.base.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsMenu")
public class MenuBean extends BaseBean{

	private String menuCode;
	private String menuName;
	private String menuUrl;
	private String superMenuCode;
	private String usingStatus;
	private String level;

	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public String getSuperMenuCode() {
		return superMenuCode;
	}
	public void setSuperMenuCode(String superMenuCode) {
		this.superMenuCode = superMenuCode;
	}

	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getUsingStatus() {
		return usingStatus;
	}
	public void setUsingStatus(String usingStatus) {
		this.usingStatus = usingStatus;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

}

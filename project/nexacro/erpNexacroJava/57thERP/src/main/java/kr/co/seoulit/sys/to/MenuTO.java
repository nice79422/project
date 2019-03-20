package kr.co.seoulit.sys.to;


import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_menu")
public class MenuTO extends BaseTO{

	String menuCode,
	superMenuCode,
	menuName,
	menuUrl,
	usingStatus,
	level;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getSuperMenuCode() {
		return superMenuCode;
	}

	public void setSuperMenuCode(String superMenuCode) {
		this.superMenuCode = superMenuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
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

}

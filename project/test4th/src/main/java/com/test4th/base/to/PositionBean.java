package com.test4th.base.to;

import java.util.List;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;
import com.test4th.hr.circumstance.to.HobongBean;

@Dataset(name="dsPosition")
public class PositionBean extends BaseBean{

	private String positionCode;
	private String positionName;
    private List<HobongBean> hobongList;
    
	public List<HobongBean> getHobongList() {
		return hobongList;
	}
	public void setHobongList(List<HobongBean> hobongList) {
		this.hobongList = hobongList;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

}

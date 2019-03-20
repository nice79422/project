package com.test.base.dao;

import java.util.List;

import com.test.base.to.ZipCodeBean;

public interface ZipCodeDAO {
	public List<ZipCodeBean> selectZipcodeList(String dong);
}

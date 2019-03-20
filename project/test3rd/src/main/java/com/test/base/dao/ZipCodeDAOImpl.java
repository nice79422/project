package com.test.base.dao;

import java.util.List;

import com.test.base.to.ZipCodeBean;


public class ZipCodeDAOImpl extends IBatisDAO implements ZipCodeDAO{
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<ZipCodeBean> selectZipcodeList(String dong){
	
		return (List<ZipCodeBean>)getSqlMapClientTemplate().queryForList("post.selectZipcodeList", dong);
}
}


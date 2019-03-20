package com.test.base.dao;

import java.util.List;

import com.test.base.to.BasicCodeBean;
import com.test.common.exception.DataAccessException;

public interface BasicCodeDAO {
	public int selectRowCount() throws DataAccessException;
	public List<BasicCodeBean> selectDetailCodeList(int sr, int er, String code) throws DataAccessException;
	public List<BasicCodeBean> selectBasicCodeList(int sr, int er) throws DataAccessException;
	public void insertDetailCode(String detailCode, String detailCodeName, String basicCode) throws DataAccessException;
	public void updateDetailCode(String detailCode, String detailCodeName, String basicCode);
	public void deleteDetailCode(String detailCode);
}

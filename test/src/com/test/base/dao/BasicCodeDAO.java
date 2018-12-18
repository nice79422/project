package com.test.base.dao;

import java.util.List;

import com.test.common.exception.DataAccessException;
import com.test.common.to.BasicCodeBean;

public interface BasicCodeDAO {
	public int selectRowCount() throws DataAccessException;
	public List<BasicCodeBean> selectDetailCodeList(int sr, int er, String code) throws DataAccessException;
	public List<BasicCodeBean> selectBasicCodeList(int sr, int er, String code) throws DataAccessException;
	public void insertDetailCode(String dCodeNo, String dCodeName, String CodeNo) throws DataAccessException;
	public void updateDetailCode(String dCodeNo, String dCodeName, String CodeNo);
	public void deleteDetailCode(String dCodeNo);
}

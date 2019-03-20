package com.test.logistics.business.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.business.to.ClientBean;

public interface ClientDAO {
	public int selectRowCount();
	public List<ClientBean> selectClientList(int sr, int er) throws DataAccessException;
	public void insertClient(ClientBean client) throws DataAccessException;
	public String selectClient(String name, String regionNo)  throws DataAccessException;
}

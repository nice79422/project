package com.test.logistics.business.dao;

import java.util.List;

import com.test.logistics.business.to.ClientBean;

public interface ClientDAO {
	public int selectRowCount();
	public List<ClientBean> selectClientList(int sr, int er);
	public void insertClient(ClientBean client);
	/*public String selectClient(String name, String regionNo);*/
}

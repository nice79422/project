package com.test.logistics.business.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.business.to.ClientBean;

public class ClientDAOImpl extends IBatisDAO implements ClientDAO {
	
	@SuppressWarnings("deprecation")
	public int selectRowCount() {
		return (int) getSqlMapClientTemplate().queryForObject("client.selectRowCount");
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<ClientBean> selectClientList(int sr, int er) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<ClientBean>) getSqlMapClientTemplate().queryForList("client.selectClientList", map);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertClient(ClientBean client){
		getSqlMapClientTemplate().update("client.insertClient", client);
	}


}

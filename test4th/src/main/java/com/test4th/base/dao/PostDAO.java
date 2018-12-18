package com.test4th.base.dao;

import java.util.List;
import java.util.Map;

import com.test4th.base.to.AddressBean;
import com.test4th.base.to.SidoBean;
import com.test4th.base.to.SigunguBean;

public interface PostDAO {
	public List<SidoBean> findSido();
	public List<SigunguBean> findSiGunGuList(String sido);
	public List<AddressBean> findAddrList(Map<String, Object> addrList);
}


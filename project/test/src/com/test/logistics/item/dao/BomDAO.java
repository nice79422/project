package com.test.logistics.item.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.item.to.BomBean;

public interface BomDAO {
	List<BomBean> selectBom(String searchCode);
	List<BomBean> selectBomList();
	public void insertBom(BomBean bom) throws DataAccessException;
}

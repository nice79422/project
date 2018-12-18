package com.test.logistics.product.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.product.to.MpsBean;

public interface MpsDAO {
	int selectMpsListRowCount(String mpsApplyStatus);
	int selectMpsListRowCount(String mpsApplyStatus, String mrpApplyStatus, String workplaceCode);
	List<MpsBean> selectMpsList(int sr, int er);
	List<MpsBean> selectMpsList(int sr, int er, String mpsApplyStatus);
	List<MpsBean> selectMpsList(int sr, int er, String mpsApplyStatus, String mrpApplyStatus,String workplaceCode,String sDate, String eDate );
	List<MpsBean> selectMpsList(int sr, int er, String mpsApplyStatus, String mrpApplyStatus);
	void insertMps(MpsBean mpsBean) throws DataAccessException;
	void updateMps(MpsBean mpsBean);

	int selectProductRowCount(String code);
	List<MpsBean> selectProductList(int sr, int er, String code);
//	public void updateProduct(ProductBean bean);
}

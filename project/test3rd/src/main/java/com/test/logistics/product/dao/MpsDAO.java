package com.test.logistics.product.dao;

import java.util.List;

import com.test.common.exception.DataAccessException;
import com.test.logistics.product.to.MpsBean;

public interface MpsDAO {
	int selectMpsListRowCount(String mpsApplyStatus);
	int selectMpsLineListRowCount(String mpsApplyStatus, String mrpApplyStatus, String workplaceCode);
	List<MpsBean> selectNotApplyMpsList(int sr, int er, String mpsApplyStatus);
	List<MpsBean> selectMpsList(int sr, int er, String mpsApplyStatus, String mrpApplyStatus,String workplaceCode,String sDate, String eDate );
	/*List<MpsBean> selectApplyMpsList(int sr, int er, String mpsApplyStatus, String mrpApplyStatus);*/
	void insertMps(MpsBean mpsBean) throws DataAccessException;
	void updateMps(MpsBean mpsBean);
	List<MpsBean> selectApplyMpsList(int sr, int er);
}

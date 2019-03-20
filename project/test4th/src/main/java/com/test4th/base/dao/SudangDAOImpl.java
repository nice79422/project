package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.EtcSalBean;
import com.test4th.base.to.OvertimeSalBean;
import com.test4th.common.dao.IBatisDAO;

public class SudangDAOImpl extends IBatisDAO implements SudangDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 연장, 야간, 기타수당목록을 가져오는 메서드 */
	public List<OvertimeSalBean> selectOvertimeSalList() {
		return getSqlMapClientTemplate().queryForList("sudang.selectOvertimeSalList");
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 기타수당목록을 가져오는 메서드 */
	public List<EtcSalBean> selectEtcSalList() {
		return getSqlMapClientTemplate().queryForList("sudang.selectEtcSalList");
	}

	// 연장수당 기타수당 등록 수정 삭제
	@SuppressWarnings("deprecation")
	@Override
	public void insertOvertimeSal(OvertimeSalBean overtimeSalBean) {
		getSqlMapClientTemplate().insert("sudang.insertOvertimeSal",overtimeSalBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateOvertimeSal(OvertimeSalBean overtimeSalBean) {
		getSqlMapClientTemplate().update("sudang.updateOvertimeSal",overtimeSalBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteOvertimeSal(OvertimeSalBean overtimeSalBean) {
		getSqlMapClientTemplate().delete("sudang.deleteOvertimeSal",overtimeSalBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertEtcSal(EtcSalBean etcSalBean) {
		getSqlMapClientTemplate().insert("sudang.insertEtcSal",etcSalBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateEtcSal(EtcSalBean etcSalBean) {
		getSqlMapClientTemplate().update("sudang.updateEtcSal",etcSalBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteEtcSal(EtcSalBean etcSalBean) {
		getSqlMapClientTemplate().delete("sudang.deleteEtcSal",etcSalBean);
	}
	

}

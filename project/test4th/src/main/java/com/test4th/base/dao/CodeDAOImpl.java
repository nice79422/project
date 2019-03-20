package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.CodeBean;
import com.test4th.base.to.DetailCodeBean;
import com.test4th.common.dao.IBatisDAO;

public class CodeDAOImpl extends IBatisDAO implements CodeDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 코드목록을 반환하는 메서드 */
	public List<CodeBean> selectCodeList() {
		return getSqlMapClientTemplate().queryForList("code.findCodeList");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertDetailCode(DetailCodeBean detailCodeBean) {
		getSqlMapClientTemplate().insert("detailCode.insertDetailCode",detailCodeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateDetailCode(DetailCodeBean detailCodeBean) {
		getSqlMapClientTemplate().update("detailCode.updateDetailCode",detailCodeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteDetailCode(DetailCodeBean detailCodeBean) {
		getSqlMapClientTemplate().delete("detailCode.deleteDetailCode",detailCodeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertCode(CodeBean codeBean) {
		getSqlMapClientTemplate().insert("code.insertCode",codeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateCode(CodeBean codeBean) {
		getSqlMapClientTemplate().update("code.updateCode",codeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteCode(CodeBean codeBean) {
		getSqlMapClientTemplate().delete("code.deleteCode",codeBean);
	}

}

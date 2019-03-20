package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.dao.CodeDAO;
import com.test4th.base.to.CodeBean;
import com.test4th.base.to.CodeInfoBean;
import com.test4th.base.to.DetailCodeBean;



public class CodeAppServiceImpl implements CodeAppService {
	/* CodeDAO setting */
    private CodeDAO codeDAO;
	public void setCodeDAO(CodeDAO codeDAO) {
		this.codeDAO = codeDAO;
	}

	/* 코드목록을 반환하는 메서드 */
	@Override
	public List<CodeBean> findCodeList() {
		return codeDAO.selectCodeList();
	}
	

	 //코드관련정보를 일괄적으로 처리하는 메서드 (코드 + 상세코드)
	@Override
	public void batchCode(CodeInfoBean codeInfoBean) {
		for(CodeBean codeBean :codeInfoBean.getCodeList()){
			switch(codeBean.getStatus()){
				case "insert" : codeDAO.insertCode(codeBean); break;
				case "update" : codeDAO.updateCode(codeBean); break;
				case "delete" : codeDAO.deleteCode(codeBean); break;
			}

		}

		for(DetailCodeBean detailCodeBean:codeInfoBean.getDetailCodeList()){
			switch(detailCodeBean.getStatus()){
				case "insert" : codeDAO.insertDetailCode(detailCodeBean); break;
				case "update" : codeDAO.updateDetailCode(detailCodeBean); break;
				case "delete" : codeDAO.deleteDetailCode(detailCodeBean); break;
			}
		}
	}

	// 상세코드관련정보를 일괄적으로 처리하는 메서드 
	@Override
	public void batchDetailCode(List<DetailCodeBean> detailCodeList) {
		for(DetailCodeBean detailCodeBean:detailCodeList){
			switch(detailCodeBean.getStatus()){
				case "insert" : codeDAO.insertDetailCode(detailCodeBean); break;
				case "update" : codeDAO.updateDetailCode(detailCodeBean); break;
				case "delete" : codeDAO.deleteDetailCode(detailCodeBean); break;
			}
		}
	}
	

}

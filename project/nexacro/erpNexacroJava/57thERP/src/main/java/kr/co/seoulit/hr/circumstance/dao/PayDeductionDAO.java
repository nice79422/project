package kr.co.seoulit.hr.circumstance.dao;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.PayDeductionItemTO;

public interface PayDeductionDAO {
	public List<PayDeductionItemTO> selectPayDeductionList();
	
	public void insertPayDeduction(PayDeductionItemTO payDeductionItemTO);
	public void updatePayDeduction(PayDeductionItemTO payDeductionItemTO);
	public void deletePayDeduction(PayDeductionItemTO payDeductionItemTO);
}

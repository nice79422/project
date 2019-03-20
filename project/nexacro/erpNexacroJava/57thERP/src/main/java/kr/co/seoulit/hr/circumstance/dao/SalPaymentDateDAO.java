package kr.co.seoulit.hr.circumstance.dao;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;



public interface SalPaymentDateDAO {
	public List<SalPaymentDateTO> selectSalPaymentDateList(String inputedYearMonth);
		
	public void insertSalPaymentDate(SalPaymentDateTO salPaymentDateTO);
	public void updateSalPaymentDate(SalPaymentDateTO salPaymentDateTO);
	public void deleteSalPaymentDate(SalPaymentDateTO salPaymentDateTO);
}

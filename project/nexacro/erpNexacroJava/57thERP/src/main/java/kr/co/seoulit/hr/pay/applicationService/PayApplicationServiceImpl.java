package kr.co.seoulit.hr.pay.applicationService;
/**
 * @Package  com.seoul.erp.common.hr
 * @Class    PayApplicationServiceImpl.java
 * @Create   2019. 02. 15.
 * @Author   허용석
 * @Description 급여계산 프로시저 호출 클래스
 *
 * @LastUpdated
 *      변수를 MAP으로 담아 보내도록 수정 : 2019. 02. 15. by 허용석
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.pay.dao.PayDAO;
import kr.co.seoulit.hr.pay.to.SalaryInputTO;

@Component
public class PayApplicationServiceImpl implements PayApplicationService{
	/*  PayDAO setting */
	@Autowired
	private PayDAO payDAO;


	/* 급여를 계산하는 메서드 */
	@Override
	public List<SalaryInputTO> payCalculate(Map<String,Object> vList) throws ProcedureException {

		Map<String, String> map =new HashMap<>();

		payDAO.payCalculate(vList);

		if((Integer)vList.get("errorCode")<0) {
	        int errorCode = (Integer) vList.get("errorCode");
			System.out.println("sadasdsadasdasdasdsadProcedureException");
			throw new ProcedureException((String) vList.get("errorMsg"));
			}

		return payDAO.selectSalaryInputList(vList); // 급여 조회
	}
}



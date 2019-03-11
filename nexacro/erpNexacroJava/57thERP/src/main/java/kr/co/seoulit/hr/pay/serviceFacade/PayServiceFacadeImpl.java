package kr.co.seoulit.hr.pay.serviceFacade;
/**
 * @Package  com.seoul.erp.common.hr
 * @Class    PayServiceFacadeImpl.java
 * @Create   2019. 02. 15.
 * @Author   허용석
 * @Description 급여계산 프로시저 호출 클래스
 *
 * @LastUpdated
 *      변수를 MAP으로 담아 보내도록 수정 : 2019. 02. 15. by 허용석
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.pay.applicationService.PayApplicationService;
import kr.co.seoulit.hr.pay.to.SalaryInputTO;

@Service
public class PayServiceFacadeImpl implements PayServiceFacade{

	@Autowired
	PayApplicationService payAppService;
	@Override
	public List<SalaryInputTO> payCalculate(Map<String, Object> vList) throws ProcedureException {
		// TODO Auto-generated method stub
		return payAppService.payCalculate(vList);
	}

}

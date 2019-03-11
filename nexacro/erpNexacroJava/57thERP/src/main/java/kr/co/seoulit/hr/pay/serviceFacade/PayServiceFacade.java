package kr.co.seoulit.hr.pay.serviceFacade;
/**
 * @Package  com.seoul.erp.common.hr
 * @Class    PayServiceFacade.java
 * @Create   2019. 02. 15.
 * @Author   허용석
 * @Description 급여계산 프로시저 호출 클래스
 *
 * @LastUpdated
 *      변수를 MAP으로 담아 보내도록 수정 : 2019. 02. 15. by 허용석
 */
import java.util.List;
import java.util.Map;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.pay.to.SalaryInputTO;

public interface PayServiceFacade {

	public List<SalaryInputTO> payCalculate(Map<String,Object> vList) throws ProcedureException;

}

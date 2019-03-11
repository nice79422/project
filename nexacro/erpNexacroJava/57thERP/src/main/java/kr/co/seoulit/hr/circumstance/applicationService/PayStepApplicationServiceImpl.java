package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.circumstance.dao.PayStepDAO;
import kr.co.seoulit.hr.circumstance.to.PayStepTO;


@Component
public class PayStepApplicationServiceImpl implements PayStepApplicationService{

	@Autowired
	private PayStepDAO payStepDAO;

	@Override
	/* 호봉목록을 가지고 오는 메서드 */
	public List<PayStepTO> findPayStepList() {
		// TODO Auto-generated method stub
		return payStepDAO.selectDirectPayStepList();
	}

	@Override
	/* 호봉관련처리를 일괄적으로 하는메서드 */
	public void batchPayStepList(List<PayStepTO> payStepList) {
		// TODO Auto-generated method stub
		for(PayStepTO payStepTO : payStepList) {
			System.out.println(payStepTO.getStatus());
			switch (payStepTO.getStatus()) {

			case "inserted":
				payStepDAO.insertPayStep(payStepTO);
				break;
			case "updated":
				payStepDAO.updatePayStep(payStepTO);
				break;
			case "deleted":
				payStepDAO.deletePayStep(payStepTO);
				break;

			}
		}		
	}

}

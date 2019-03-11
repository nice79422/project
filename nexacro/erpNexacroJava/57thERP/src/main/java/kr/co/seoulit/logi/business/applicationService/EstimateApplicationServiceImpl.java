package kr.co.seoulit.logi.business.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.business.dao.ContractDAO;
import kr.co.seoulit.logi.business.dao.ContractDetailDAO;
import kr.co.seoulit.logi.business.dao.DeliveryResultDAO;
import kr.co.seoulit.logi.business.dao.EstimateDAO;
import kr.co.seoulit.logi.business.dao.EstimateDetailDAO;
import kr.co.seoulit.logi.business.to.CompleteDeliveryResultTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;
import kr.co.seoulit.logi.purchase.applicationService.StockApplicationService;
import kr.co.seoulit.logi.business.to.DeliveryResultTO;
import kr.co.seoulit.logi.purchase.to.StockTO;

@Component
public class EstimateApplicationServiceImpl implements EstimateApplicationService {
	@Autowired
	private EstimateDAO estimateDAO;
	@Autowired
	private EstimateDetailDAO estimateDetailDAO;
	
	@Override
	public List<EstimateTO> findEstimateList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return estimateDAO.selectEstimateList(searchDate);
	}

	@Override
	public List<EstimateTO> findEstimateDialog() {
		// TODO Auto-generated method stub
		return estimateDAO.selectEstimateDialog();
	}

	@Override
	public void batchEstimate(List<EstimateTO> estimateList, List<EstimateDetailTO> estimateDetailList) {
		// TODO Auto-generated method stub
		System.out.println("c");
		for (EstimateTO estimateTO : estimateList) {
			switch (estimateTO.getStatus()) {
			case "inserted": estimateDAO.insertEstimate(estimateTO); break;
			case "updated": estimateDAO.updateEstimate(estimateTO); break;
			case "deleted": estimateDAO.deleteEstimate(estimateTO); break;

			}

		}
		System.out.println("d");
		if (estimateDetailList != null) {
			System.out.println("+++"+estimateDetailList.get(0).getEstimateNo());
			for (EstimateDetailTO estimateDetailTO : estimateDetailList) {
				switch (estimateDetailTO.getStatus()) {
				case "inserted": estimateDetailDAO.insertEstimateDetail(estimateDetailTO); break;
				case "updated": estimateDetailDAO.updateEstimateDetail(estimateDetailTO); break;
				case "deleted": estimateDetailDAO.deleteEstimateDetail(estimateDetailTO); break;

				}

			}

		}

	}

	@Override
	public List<EstimateDetailTO> findEstimateDetailList(String estimateNo) {
		// TODO Auto-generated method stub
		return estimateDetailDAO.selectEstimateDetailList(estimateNo);
	}
}

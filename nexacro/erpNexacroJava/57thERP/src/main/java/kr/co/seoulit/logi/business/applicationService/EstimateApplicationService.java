package kr.co.seoulit.logi.business.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;

public interface EstimateApplicationService {

	List<EstimateTO> findEstimateList(HashMap<String, Object> searchDate);

	List<EstimateTO> findEstimateDialog();

	void batchEstimate(List<EstimateTO> estimateList, List<EstimateDetailTO> estimateDetailList);

	List<EstimateDetailTO> findEstimateDetailList(String estimateNo);

}

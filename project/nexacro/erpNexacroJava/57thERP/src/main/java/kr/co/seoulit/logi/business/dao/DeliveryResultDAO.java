package kr.co.seoulit.logi.business.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.CompleteDeliveryResultTO;
import kr.co.seoulit.logi.business.to.DeliveryResultTO;

public interface DeliveryResultDAO {

	public List<DeliveryResultTO> selectDeliveryResultList();

	public List<CompleteDeliveryResultTO> selectCompleteDeliveryResultList(HashMap<String, Object> searchDate);

	public void insertDeliveryResult(DeliveryResultTO selectDeliveryTO);

	public void updateDeliveryResult(DeliveryResultTO selectDeliveryTO);
}

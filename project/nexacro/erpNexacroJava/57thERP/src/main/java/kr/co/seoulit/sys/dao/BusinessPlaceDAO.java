package kr.co.seoulit.sys.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.sys.to.BusinessPlaceTO;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.DepartmentTO;

public interface BusinessPlaceDAO{

	public List<BusinessPlaceTO> selectBusinessPlaceList();
	
	public void insertBusinessPlace(BusinessPlaceTO businessPlaceTO);
	
	public void updateBusinessPlace(BusinessPlaceTO businessPlaceTO);
	
	public void deleteBusinessPlace(BusinessPlaceTO businessPlaceTO);

}

package kr.co.seoulit.hr.pm.dao;

import java.util.List;

import kr.co.seoulit.hr.pm.to.EducationInfoTO;

public interface EducationInfoDAO {
    public List<EducationInfoTO> selectEducationInfoAll();
    public void insertEducationInfo(EducationInfoTO educationInfoTO);
	public void updateEducationInfo(EducationInfoTO educationInfoTO);
	public void deleteEducationInfo(EducationInfoTO educationInfoTO);
}

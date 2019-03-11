package kr.co.seoulit.hr.pm.dao;

import java.util.List;
import kr.co.seoulit.hr.pm.to.WorkInfoTO;

public interface WorkInfoDAO {
    public List<WorkInfoTO> selectWorkInfoAll();
    
    public void updateWorkInfo(WorkInfoTO workInfoTO);
}

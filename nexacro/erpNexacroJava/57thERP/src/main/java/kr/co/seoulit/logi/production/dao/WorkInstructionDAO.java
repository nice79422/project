package kr.co.seoulit.logi.production.dao;

import java.util.List;
import java.util.Map;

import kr.co.seoulit.logi.production.to.MaterialCheckTempTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;

public interface WorkInstructionDAO {

	public List<MaterialCheckTempTO> materialCheckTempList(Map<String,Object> parameters);
	
	public List<WorkInstructionTO> selectWorkInstructionList();
	
	public List<WorkInstructionTO> selectWorkInstructionList2();
	
	public void insertWorkInstruction(WorkInstructionTO workInstructionTO);

	public void updateWorkInstruction(WorkInstructionTO workInstructionTO);

	public void deleteWorkInstruction(WorkInstructionTO workInstructionTO);
}

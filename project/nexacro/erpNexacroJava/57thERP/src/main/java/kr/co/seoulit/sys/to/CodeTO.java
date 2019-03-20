package kr.co.seoulit.sys.to;

import java.util.List;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_code")
public class CodeTO extends BaseTO{

	String code, codeName, editStatus, codeGroup;
	List<CodeDetailTO> codeDetailList;

	public List<CodeDetailTO> getCodeDetailList() {
		return codeDetailList;
	}

	public void setCodeDetailList(List<CodeDetailTO> codeDetailList) {
		this.codeDetailList = codeDetailList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}

	public String getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}



}

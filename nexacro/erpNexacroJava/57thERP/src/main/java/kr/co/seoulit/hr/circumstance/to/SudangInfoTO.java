package kr.co.seoulit.hr.circumstance.to;

import java.util.List;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

public class SudangInfoTO extends BaseTO{

	private List<OvertimeSudangTO> overtimeSalList;
	private List<EtcSudangTO> etcSalList;

	public List<OvertimeSudangTO> getovertimeSalList() {
		return overtimeSalList;
	}

	public void setovertimeSalList(List<OvertimeSudangTO> overtimeSalList) {
		this.overtimeSalList = overtimeSalList;
	}

	public List<EtcSudangTO> getEtcSalList() {
		return etcSalList;
	}
	public void setEtcSalList(List<EtcSudangTO> etcSalList) {
		this.etcSalList = etcSalList;
	}

}

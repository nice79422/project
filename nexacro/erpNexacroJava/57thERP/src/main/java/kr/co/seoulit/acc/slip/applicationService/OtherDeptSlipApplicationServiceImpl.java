package kr.co.seoulit.acc.slip.applicationService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.acc.slip.dao.JournalDAO;
import kr.co.seoulit.acc.slip.dao.SlipDAO;
import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.logi.business.applicationService.ContractApplicationService;
import kr.co.seoulit.logi.business.applicationService.DeliveryApplicationService;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.purchase.applicationService.OrderApplicationService;
import kr.co.seoulit.logi.purchase.applicationService.StockApplicationService;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.sys.applicationService.BaseApplicationService;

@Component
public class OtherDeptSlipApplicationServiceImpl implements OtherDeptSlipApplicationService {

	@Autowired
	private SlipDAO slipDAO;
	@Autowired
	private JournalDAO journalDAO;
	@Autowired
	private ContractApplicationService contractApplicationService;
	@Autowired
	private BaseApplicationService baseApplicationService;
	@Autowired
	private OrderApplicationService orderApplicationService;


	protected final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public List<ContractDetailTO> findContractSlipDetailList(HashMap<String, Object> logiSlipReq) {
		// TODO Auto-generated method stub
		return contractApplicationService.findRangedContractDetailList(logiSlipReq);
	}

	@Override
	public List<OrderDetailTO> findOrderSlipDetailList(HashMap<String, Object> logiSlipReq) {
		// TODO Auto-generated method stub
		return orderApplicationService.findOrderDetailList(logiSlipReq);
	}

	@Override
	public void registLogiSlipList(HashMap<String, Object> logiSlipMap) {
		// TODO Auto-generated method stub
	/*	if (logger.isDebugEnabled()) {
			logger.debug("(registLogiSlipList) 시작");
		}*/
		String req = (String) logiSlipMap.get("req");
			switch(req){
			case "수주선급" : registContractSlipList(logiSlipMap); break;
			case "납품완료" : registContractSlipList(logiSlipMap); break;
			case "발주선급" : registOrderSlipList(logiSlipMap); break;
			case "입고완료" : registOrderSlipList(logiSlipMap); break;
			}
		}
	public void registContractSlipList(HashMap<String, Object> logiSlipMap) {
		String deptCode = (String) logiSlipMap.get("deptCode");
		String empCode = (String) logiSlipMap.get("empCode");
		String reportingDate = (String) logiSlipMap.get("reportingDate");
		String businessCode = (String) logiSlipMap.get("businessCode");
		String req = (String) logiSlipMap.get("req");
		String period ="BRC-01_02"; //(String) logiSlipMap.get("period");

		List<ContractDetailTO> logiSlipList=(List<ContractDetailTO>) logiSlipMap.get("logiSlipList");
		List<ContractTO> contractTOList=(List<ContractTO>) logiSlipMap.get("contractTOList");
		List<ContractDetailTO>  contractDetailList=new ArrayList<>();

		for (ContractDetailTO logiSlip : logiSlipList) {
			for(ContractTO contractTO:contractTOList){
				if(contractTO.getContractNo().equals(logiSlip.getContractNo())){
					contractTO.setStatus("updated");
				}
			}
			HashMap<String, Object> variables = new HashMap<>();

			String slipseq = baseApplicationService.findSequenceNo("SLIP_SEQ");

			SlipTO slipTo = new SlipTO();
			slipTo.setSlipDescription(logiSlip.getContractNo());
			slipTo.setStatus("inserted");
			slipTo.setSlipStatus("미결");
			slipTo.setSlipNo(slipseq);
			slipTo.setSlipType("대체");
			slipTo.setApprovalDate("미결");
			slipTo.setApprovalEmpCode("미결");
			slipTo.setDeptCode(deptCode);
			slipTo.setReportingDate(reportingDate);
			slipTo.setReportingEmpCode(empCode);
			slipTo.setExpenseReport(req);
			slipTo.setAccountPeriodNo(period);
			List<JournalTO> journalToList = new ArrayList<>();

			variables.put("contractNo", logiSlip.getContractNo());
			ContractTO contractTO =(ContractTO) contractApplicationService.findContractTo((String) logiSlip.getContractNo()).get(0);
			contractDetailList=contractTO.getContractDetailList();

			for(ContractDetailTO contractDetailTO:contractDetailList){
				contractDetailTO.setStatus("updated");
				contractDetailTO.setDescription(logiSlip.getDescription());
			}

			for (int i = 0; i < 2; i++) {
				JournalTO journalTO = new JournalTO();
				journalTO.setStatus("inserted");
				journalTO.setJournalDescription(logiSlip.getContractDetailNo());


				String journalSeq = baseApplicationService.findSequenceNo("JOURNAL_SEQ");
				journalTO.setJournalNo(journalSeq);
				journalTO.setSlipNo(slipseq);
				journalTO.setSummaryComment(logiSlip.getItemCode() + "/" + logiSlip.getItemName());
				journalTO.setCustomerCode(contractTO.getCustomerCode());
				journalTO.setCustomerName(contractTO.getCustomerCode());

				List<JournalDetailTO> journaldetailTOList = new ArrayList<>();
				if (i == 0) {
					journalTO.setRightCreditsPrice(logiSlip.getSumPriceOfContract());
					journalTO.setLeftDebtorPrice("0");
					journalTO.setBalanceDivision("대변");
					journalTO.setAccountInnerCode("0146");
					journalTO.setAccountName("상품");

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0146");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {
						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");
						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0146");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "수량":
							journaldetailTO.setValue(logiSlip.getContractAmount());
							break;
						case "사업장":
							journaldetailTO.setValue(businessCode);
							break;
						case "선급율":
							journaldetailTO.setValue(logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}
				} else {
					journalTO.setBalanceDivision("차변");
					journalTO.setLeftDebtorPrice(logiSlip.getSumPriceOfContract());
					journalTO.setRightCreditsPrice("0");

					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0401");
					journalTO.setAccountInnerCode("0401");
					journalTO.setAccountName("상품매출");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {

						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");

						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0401");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "금융기관":
							journaldetailTO.setValue("NH농협은행");
							break;
						case "선급율":
							journaldetailTO.setValue(logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}

				}

				journalDAO.insertJournalInfo(journalTO);
			}

			slipDAO.insertSlipInfo(slipTo);
			}

		contractApplicationService.batchContract(contractTOList,contractDetailList);

	}
	public void registOrderSlipList(HashMap<String, Object> logiSlipMap) {
		String deptCode = (String) logiSlipMap.get("deptCode");
		String empCode = (String) logiSlipMap.get("empCode");
		String reportingDate = (String) logiSlipMap.get("reportingDate");
		String businessCode = (String) logiSlipMap.get("businessCode");
		String req = (String) logiSlipMap.get("req");
		String period ="BRC-01_02"; //(String) logiSlipMap.get("period");

		List<OrderDetailTO> logiSlipList=(List<OrderDetailTO>) logiSlipMap.get("logiSlipList");
		List<OrderInfoTO> orderInfoList=(List<OrderInfoTO>) logiSlipMap.get("orderInfoList");

		for (OrderDetailTO logiSlip : logiSlipList) {

			HashMap<String, Object> variables = new HashMap<>();

			String slipseq = baseApplicationService.findSequenceNo("SLIP_SEQ");

			SlipTO slipTo = new SlipTO();
			slipTo.setSlipDescription(logiSlip.getOrderDetailNo());
			slipTo.setStatus("inserted");
			slipTo.setSlipStatus("미결");
			slipTo.setSlipNo(slipseq);
			slipTo.setSlipType("대체");
			slipTo.setApprovalDate("미결");
			slipTo.setApprovalEmpCode("미결");
			slipTo.setDeptCode(deptCode);
			slipTo.setReportingDate(reportingDate);
			slipTo.setReportingEmpCode(empCode);
			slipTo.setExpenseReport(req);
			slipTo.setAccountPeriodNo(period);
			List<JournalTO> journalToList = new ArrayList<>();


			for (int i = 0; i < 2; i++) {
				JournalTO journalTO = new JournalTO();
				journalTO.setJournalDescription(logiSlip.getOrderDetailNo());
				journalTO.setStatus("inserted");
				String journalSeq = baseApplicationService.findSequenceNo("JOURNAL_SEQ");
				journalTO.setJournalNo(journalSeq);
				journalTO.setSlipNo(slipseq);
				journalTO.setSummaryComment(logiSlip.getItemCode() + "/" + logiSlip.getItemName());

				for(OrderInfoTO orderInfoTO:orderInfoList){
					if(orderInfoTO.getOrderNo().equals(logiSlip.getOrderNo())){
						orderInfoTO.setStatus("updated");
						journalTO.setCustomerCode(orderInfoTO.getCustomerCode());
						journalTO.setCustomerName(orderInfoTO.getCustomerCode());
					}
				}


				List<JournalDetailTO> journaldetailTOList = new ArrayList<>();
				if (i == 0) {
					journalTO.setRightCreditsPrice(logiSlip.getSumPriceOfOrder());
					journalTO.setLeftDebtorPrice("0");
					journalTO.setBalanceDivision("대변");
					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0501");
					journalTO.setAccountInnerCode("0501");
					journalTO.setAccountName("원재료비");

					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {
						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");
						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0501");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "금융기관":
							journaldetailTO.setValue("NH농협은행");
							break;
						case "비고":
							journaldetailTO.setValue("선급율 "+logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}
				} else {
					List<AccountControlDetailTO> accountControlDetailTOList = journalDAO.selectAccountControlDetailList("0146");
					journalTO.setBalanceDivision("차변");
					journalTO.setRightCreditsPrice("0");
					journalTO.setLeftDebtorPrice(logiSlip.getSumPriceOfOrder());
					journalTO.setAccountInnerCode("0153");
					journalTO.setAccountName("원재료");
					for (AccountControlDetailTO accountControlDetailTO : accountControlDetailTOList) {

						String journalDetailSeq = baseApplicationService.findSequenceNo("JOURNAL_DETAIL_SEQ");

						JournalDetailTO journaldetailTO = new JournalDetailTO();
						journaldetailTO.setStatus("inserted");
						journaldetailTO.setJournalNo(journalSeq);
						journaldetailTO.setAccountInnerCode("0153");
						journaldetailTO.setJournaldetailNo(journalDetailSeq);
						journaldetailTO.setItem(accountControlDetailTO.getAccountcontrolName());
						switch (accountControlDetailTO.getAccountcontrolName()) {
						case "수량":
							journaldetailTO.setValue(logiSlip.getOrderAmount());
							break;
						case "사업장":
							journaldetailTO.setValue(businessCode);
							break;
						case "선급율":
							journaldetailTO.setValue(logiSlip.getDescription());
							break;
						default:
							journaldetailTO.setValue("검증필요");
							break;
						}

						journalDAO.insertJournalDetailInfo(journaldetailTO);
					}

				}

				journalDAO.insertJournalInfo(journalTO);
			}

			slipDAO.insertSlipInfo(slipTo);
			}
		orderApplicationService.registOrder(orderInfoList, logiSlipList, null);
	}
}
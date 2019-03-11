package kr.co.seoulit.hr.appointment.serviceFacade;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.appointment.to.AppointmentTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentDetailTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentTO;
import kr.co.seoulit.hr.appointment.to.TempAppointmentTO;
import kr.co.seoulit.hr.emp.to.EmployeeTO;

public interface AppointmentServiceFacade {
	
	/*인사발령조회	*/
	public List<AppointmentTO> findAppointmentList();
	/*인사발령 사원조회	*/
	public List<PersonalAppointmentTO> findPersonalAppointmentList();
	/*인사발령 일괄처리	*/
	public void batchAppointment(List<AppointmentTO> appointment,List<PersonalAppointmentTO> personalAppointment);
	/*인사발령 개인  일괄처리	*/
	public void batchPersonalAppointment(List<PersonalAppointmentTO> personalAppointment);
	/*인사발령 사원조회 상세	*/
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList(HashMap<String,Object> appointmentDetailMap);
	/*인사발령 일괄처리	*/
	public void batchPersonalAppointmentDetail(List<AppointmentTO> appointment, List<PersonalAppointmentTO> personalAppointment, List<PersonalAppointmentDetailTO> personalAppointmentDetail, List<EmployeeTO> employeeList);
	
	/*인사발령 사원조회 상세	*/
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList2();
	
	/*프로시저	*/
	public List<TempAppointmentTO> findAppointmentHistory(HashMap<String, Object> appointmentNoMap);
	/*삭제 프로시저	*/
	public List<TempAppointmentTO> findCanceledAppointment(HashMap<String, Object> appointmentInfoMap);
	
//
//	public void batchEmployeeList(List<EmployeeTO> employeeList);
//
//	/* 호봉 관리 */
//	public List<PayStepTO> findPayStepList();
//	
//	public void batchPayStepList(List<PayStepTO> payStepList);	
//
//	/* 휴일 관리 */
//	public List<HolidayTO> findHolidayList(Map<String, Object> dateList);
//	
//	public void batchHoliday(List<HolidayTO> holidayList);
//	
//	/*연차정보 구하기*/
//	public List<AnnualLeaveTO> findAnnualLeaveList();
//	public List<AnnualLeaveTO> createAnnualLeave(String standardYear,String empCode,String hireDate) throws ProcedureException;
//	public void batchAnnualLeave(List<AnnualLeaveTO> annualLeaveList);
//	
//	/*연차신청 후 변경 사항 반영*/
//	public List<AnnualLeaveTO> editAnnualLeaveMgt(String standardYear, String empCode, String days, String restDays);	
}

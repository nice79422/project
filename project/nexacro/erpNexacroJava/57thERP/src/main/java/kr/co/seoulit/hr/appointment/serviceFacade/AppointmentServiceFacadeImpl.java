package kr.co.seoulit.hr.appointment.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.hr.appointment.applicationService.AppointmentApplicationService;
import kr.co.seoulit.hr.appointment.to.AppointmentTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentDetailTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentTO;
import kr.co.seoulit.hr.appointment.to.TempAppointmentTO;
import kr.co.seoulit.hr.emp.to.EmployeeTO;


@Service
public class AppointmentServiceFacadeImpl implements AppointmentServiceFacade{

	@Autowired
	private AppointmentApplicationService appointmentApplicationService;

	@Override
	public List<AppointmentTO> findAppointmentList() {
		// TODO Auto-generated method stub
		return appointmentApplicationService.findAppointmentList();
	}

	@Override
	public List<PersonalAppointmentTO> findPersonalAppointmentList() {
		// TODO Auto-generated method stub
		return appointmentApplicationService.findPersonalAppointmentList();
	}

	@Override
	public void batchAppointment(List<AppointmentTO> appointment,List<PersonalAppointmentTO> personalAppointment) {
		// TODO Auto-generated method stub
		appointmentApplicationService.batchAppointment(appointment,personalAppointment);
	}

	@Override
	public void batchPersonalAppointment(List<PersonalAppointmentTO> personalAppointment) {
		// TODO Auto-generated method stub
		appointmentApplicationService.batchPersonalAppointment(personalAppointment);
		
	}

	@Override
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList(HashMap<String,Object> appointmentDetailMap) {
		// TODO Auto-generated method stub
		return appointmentApplicationService.findPersonalAppointmentDetailList(appointmentDetailMap);
	}

	@Override
	public void batchPersonalAppointmentDetail(List<AppointmentTO> appointment, List<PersonalAppointmentTO> personalAppointment,
			List<PersonalAppointmentDetailTO> personalAppointmentDetail, List<EmployeeTO> employeeList) {
		// TODO Auto-generated method stub
		appointmentApplicationService.batchPersonalAppointmentDetail(appointment, personalAppointment,personalAppointmentDetail, employeeList);
		
	}
	
	@Override
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList2() {
		// TODO Auto-generated method stub
		return appointmentApplicationService.findPersonalAppointmentDetailList2();
	}

	@Override
	public List<TempAppointmentTO> findAppointmentHistory(HashMap<String, Object> appointmentNoMap) {
		// TODO Auto-generated method stub
		return appointmentApplicationService.findAppointmentHistory(appointmentNoMap);
	}

	@Override
	public List<TempAppointmentTO> findCanceledAppointment(HashMap<String, Object> appointmentInfoMap) {
		// TODO Auto-generated method stub
		return appointmentApplicationService.findCanceledAppointment(appointmentInfoMap);
	}
	
	
	//
//	@Override
//	public List<EmployeeTO> findEmployeeList() {
//		// TODO Auto-generated method stub
//
//		return empApplicationService.findEmployeeList();
//
//	}
//
//	@Override
//	public void batchEmployeeList(List<EmployeeTO> employeeList) {
//		// TODO Auto-generated method stub
//		empApplicationService.batchEmployeeList(employeeList);
//	}
//
//	@Override
//	public List<PayStepTO> findPayStepList() {
//		// TODO Auto-generated method stub
//		return payStepApplicationService.findPayStepList();
//	}
//
//	@Override
//	public void batchPayStepList(List<PayStepTO> payStepList) {
//		// TODO Auto-generated method stub
//		payStepApplicationService.batchPayStepList(payStepList);
//	}
//
//	/* 휴일목록을 가지고 오는 메서드 */
//	@Override
//	public List<HolidayTO> findHolidayList(Map<String, Object> dateList) {
//		return holidayApplicationService.findHolidayList(dateList);
//	}
//	
//	/* 휴일관련 일괄처리를 하는 메서드 */
//	@Override
//	public void batchHoliday(List<HolidayTO> holidayList) {
//		holidayApplicationService.batchHoliday(holidayList);
//	}
//
//
//	/* 연차테이블을 조회하는 메서드 */
//	@Override
//	public List<AnnualLeaveTO> findAnnualLeaveList() {
//
//		return annualLeaveApplicationService.findAnnualLeaveList();
//	}
//	
//	/* 해당년도, 해당사원의 연차정보를 생성하는 메서드 */
//	@Override
//	public List<AnnualLeaveTO> createAnnualLeave(String standardYear,String empCode,String hireDate) throws ProcedureException{
//		return annualLeaveApplicationService.createAnnualLeave(standardYear,empCode,hireDate);
//	}
//	
//	/* 연차와 관련된 일괄처리를 하는 메서드 */
//	@Override
//	public void batchAnnualLeave(List<AnnualLeaveTO> annualLeaveList) {
//		annualLeaveApplicationService.batchAnnualLeave(annualLeaveList);
//	}
//	
//	/* 연차를 신청할때 연차관리테이블을 수정하기 위한 메서드 */
//	@Override
//	public List<AnnualLeaveTO> editAnnualLeaveMgt(String standardYear, String empCode, String days, String restDays){
//		return annualLeaveApplicationService.editAnnualLeaveMgt(standardYear, empCode, days, restDays);
//	}
//


}

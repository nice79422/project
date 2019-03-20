package kr.co.seoulit.hr.appointment.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.appointment.dao.AppointmentDAO;
import kr.co.seoulit.hr.appointment.dao.PersonalAppointmentDAO;
import kr.co.seoulit.hr.appointment.dao.PersonalAppointmentDetailDAO;
import kr.co.seoulit.hr.appointment.to.AppointmentTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentDetailTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentTO;
import kr.co.seoulit.hr.appointment.to.TempAppointmentTO;
import kr.co.seoulit.hr.emp.applicationService.EmpApplicationService;
import kr.co.seoulit.hr.emp.to.EmployeeTO;


@Component
public class AppointmentApplicationServiceImpl implements AppointmentApplicationService{

	@Autowired
	private AppointmentDAO appointmentDAO;
	
	@Autowired
	private PersonalAppointmentDAO personalAppointmentDAO;

	@Autowired
	private PersonalAppointmentDetailDAO personalAppointmentDetailDAO;
	
	@Autowired
	private EmpApplicationService empApplicationService;

	@Override
	public List<AppointmentTO> findAppointmentList() {
		// TODO Auto-generated method stub
		return appointmentDAO.selectAppointment();
	}

	@Override
	public List<PersonalAppointmentTO> findPersonalAppointmentList() {
		// TODO Auto-generated method stub
		return personalAppointmentDAO.selectPersonalAppointment();
	}

	@Override
	public void batchAppointment(List<AppointmentTO> appointment,List<PersonalAppointmentTO> personalAppointment) {
		// TODO Auto-generated method stub
		for(AppointmentTO appointmentTO : appointment){
			System.out.println("&&&&&&&&&& :  "+appointmentTO.getStatus());
			switch(appointmentTO.getStatus()){
				case "inserted" :
					appointmentDAO.insertAppointment(appointmentTO);
					break;
				case "updated" :
					appointmentDAO.updateAppointment(appointmentTO);
					break;	
				case "deleted" :
					appointmentDAO.deleteAppointment(appointmentTO);
					break;
			}
		}
		if(personalAppointment!=null){
			batchPersonalAppointment(personalAppointment);
		}
	}

	@Override
	public void batchPersonalAppointment(List<PersonalAppointmentTO> personalAppointment) {
		// TODO Auto-generated method stub
		for(PersonalAppointmentTO personalAppointmentTO : personalAppointment){
			System.out.println("&&&&&&&&&& personalAppointmentTO.getStatus() :  "+personalAppointmentTO.getStatus());
			switch(personalAppointmentTO.getStatus()){
				case "inserted" :
					personalAppointmentDAO.insertPersonalAppointment(personalAppointmentTO);
					break;
				case "updated" :
					personalAppointmentDAO.updatePersonalAppointment(personalAppointmentTO);
					break;	
				case "deleted" :
					personalAppointmentDAO.deletePersonalAppointment(personalAppointmentTO);
					break;
			}
		}
	}

	@Override
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList(HashMap<String,Object> appointmentDetailMap) {
		// TODO Auto-generated method stub
		return personalAppointmentDetailDAO.selectPersonalAppointmentDetail(appointmentDetailMap);
	}

	@Override
	public void batchPersonalAppointmentDetail(List<AppointmentTO> appointment, List<PersonalAppointmentTO> personalAppointment,
			List<PersonalAppointmentDetailTO> personalAppointmentDetail, List<EmployeeTO> employeeList) {
		// TODO Auto-generated method stub
		for(PersonalAppointmentDetailTO personalAppointmentDetailTO : personalAppointmentDetail){
			System.out.println("&&&&&&&&&& personalAppointmentDetailTO.getStatus() :  "+personalAppointmentDetailTO.getStatus());
			switch(personalAppointmentDetailTO.getStatus()){
				case "inserted" :
					personalAppointmentDetailDAO.insertPersonalAppointmentDetail(personalAppointmentDetailTO);
					break;
				case "updated" :
					personalAppointmentDetailDAO.updatePersonalAppointmentDetail(personalAppointmentDetailTO);
					break;	
				case "deleted" :
					personalAppointmentDetailDAO.deletePersonalAppointmentDetail(personalAppointmentDetailTO);
					break;
			}
		}
		if(appointment!=null){
			batchAppointment(appointment,personalAppointment);
		}
//		if(personalAppointment!=null){
//			batchPersonalAppointment(personalAppointment);
//		}
		if(employeeList!=null){
			empApplicationService.batchEmployeeList(employeeList);
		}
	}
	
	
	
	@Override
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList2() {
		// TODO Auto-generated method stub
		return personalAppointmentDetailDAO.selectPersonalAppointmentDetail2();
	}

	@Override
	public List<TempAppointmentTO> findAppointmentHistory(HashMap<String, Object> appointmentNoMap) {
		// TODO Auto-generated method stub
		return personalAppointmentDetailDAO.selectAppointmentHistory(appointmentNoMap);
	}

	@Override
	public List<TempAppointmentTO> findCanceledAppointment(HashMap<String, Object> appointmentInfoMap) {
		// TODO Auto-generated method stub
		return personalAppointmentDetailDAO.selectCanceledAppointment(appointmentInfoMap);
	}
	
	
}

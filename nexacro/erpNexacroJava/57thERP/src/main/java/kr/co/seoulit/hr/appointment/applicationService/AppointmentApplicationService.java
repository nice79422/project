package kr.co.seoulit.hr.appointment.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.hr.appointment.to.AppointmentTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentDetailTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentTO;
import kr.co.seoulit.hr.appointment.to.TempAppointmentTO;
import kr.co.seoulit.hr.emp.to.EmployeeTO;

public interface AppointmentApplicationService {

	public List<AppointmentTO> findAppointmentList();
	public List<PersonalAppointmentTO> findPersonalAppointmentList();
	public void batchAppointment(List<AppointmentTO> appointment,List<PersonalAppointmentTO> personalAppointment);
	public void batchPersonalAppointment(List<PersonalAppointmentTO> personalAppointment);
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList(HashMap<String,Object> appointmentDetailMap);
	public List<PersonalAppointmentDetailTO> findPersonalAppointmentDetailList2();
	public void batchPersonalAppointmentDetail(List<AppointmentTO> appointment, List<PersonalAppointmentTO> personalAppointment, List<PersonalAppointmentDetailTO> personalAppointmentDetail, List<EmployeeTO> employeeList);

	public List<TempAppointmentTO> findAppointmentHistory(HashMap<String, Object> appointmentNoMap);
	public List<TempAppointmentTO> findCanceledAppointment(HashMap<String, Object> appointmentInfoMap);
	
//	public List<EmployeeTO> findEmployeeList();
//	public EmployeeTO findEmployee(String empCode);
//	public void batchEmployeeList(List<EmployeeTO> employeeList);
//	
}

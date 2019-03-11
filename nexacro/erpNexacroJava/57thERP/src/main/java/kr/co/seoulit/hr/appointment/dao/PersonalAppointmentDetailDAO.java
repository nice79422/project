package kr.co.seoulit.hr.appointment.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.hr.appointment.to.PersonalAppointmentDetailTO;
import kr.co.seoulit.hr.appointment.to.TempAppointmentTO;

public interface PersonalAppointmentDetailDAO {
	
	public List<PersonalAppointmentDetailTO> selectPersonalAppointmentDetail(HashMap<String,Object> appointmentDetailMap);
	public List<PersonalAppointmentDetailTO> selectPersonalAppointmentDetail2();
	public void insertPersonalAppointmentDetail(PersonalAppointmentDetailTO personalAppointmentDetailTO);
	public void updatePersonalAppointmentDetail(PersonalAppointmentDetailTO personalAppointmentDetailTO);
	public void deletePersonalAppointmentDetail(PersonalAppointmentDetailTO personalAppointmentDetailTO);
	public List<TempAppointmentTO> selectAppointmentHistory(HashMap<String, Object> appointmentNoMap);
	public List<TempAppointmentTO> selectCanceledAppointment(HashMap<String, Object> appointmentInfoMap);
}
package kr.co.seoulit.angular.serviceFacade;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.angular.exception.IdNotFoundException;
import kr.co.seoulit.angular.exception.PwMissMatchException;
import kr.co.seoulit.angular.to.MemberTO;

public interface AngularServiceFacade {
	
	public List<MemberTO> findMemberList();
	public void joinMember(MemberTO member);
	public void deleteMember(MemberTO member);
	public MemberTO login(String id,String pw) throws IdNotFoundException, PwMissMatchException;
}

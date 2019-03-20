package kr.co.seoulit.angular.serviceFacade;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.angular.to.MemberTO;
import kr.co.seoulit.angular.applicationService.AngularApplicationService;
import kr.co.seoulit.angular.exception.IdNotFoundException;
import kr.co.seoulit.angular.exception.PwMissMatchException;


@Service
public class AngularServiceFacadeImpl implements AngularServiceFacade{
	@Autowired
	private AngularApplicationService baseApplicationService;

	@Override
	public List<MemberTO> findMemberList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findMemberList();
	}

	@Override
	public void joinMember(MemberTO member) {
		// TODO Auto-generated method stub
		baseApplicationService.joinMember(member);
		
	}
	
	@Override
	public void deleteMember(MemberTO member) {
		// TODO Auto-generated method stub
		baseApplicationService.deleteMember(member);
		
	}


	@Override
	public MemberTO login(String id,String pw)
			throws IdNotFoundException, PwMissMatchException {
		// TODO Auto-generated method stub
		return baseApplicationService.login(id,pw);
	}
}

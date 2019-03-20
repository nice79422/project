
package kr.co.seoulit.angular.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.angular.dao.MemberDAO;
import kr.co.seoulit.angular.exception.IdNotFoundException;
import kr.co.seoulit.angular.exception.PwMissMatchException;
import kr.co.seoulit.angular.to.MemberTO;

@Component
public class AngularApplicationServiceImpl implements AngularApplicationService {

	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List<MemberTO> findMemberList() {
		// TODO Auto-generated method stub
		return memberDAO.selectMemberList();
	}

	@Override
	public void joinMember(MemberTO member) {
		// TODO Auto-generated method stub
		memberDAO.insertMember(member);
	}
	

	@Override
	public void deleteMember(MemberTO member) {
		// TODO Auto-generated method stub
		memberDAO.deleteMember(member);
	}
	

	@Override
	public MemberTO login(String id, String pw)
			throws IdNotFoundException,  PwMissMatchException {
		// TODO Auto-generated method stub
		MemberTO member=memberDAO.login(id);
		
		if(member==null) {
			throw new IdNotFoundException("존재하는 ID가 없습니다.");
		}
		else {
				if(member.getPw().equals(pw)) {
					member.setPw("null");
					return member;
				}
				else {
					throw new PwMissMatchException("사원의 비밀번호가 일치하지 않습니다.");
					
				}
				
			}
		}
	}	
	
	



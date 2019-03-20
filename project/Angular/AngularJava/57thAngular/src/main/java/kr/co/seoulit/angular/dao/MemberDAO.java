package kr.co.seoulit.angular.dao;

import java.util.List;

import kr.co.seoulit.angular.to.MemberTO;

public interface MemberDAO{

	public List<MemberTO> selectMemberList();
	public void insertMember(MemberTO member);
	public void deleteMember(MemberTO member);
	public MemberTO login(String id);
}

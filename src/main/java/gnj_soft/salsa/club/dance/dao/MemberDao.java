package gnj_soft.salsa.club.dance.dao;

import java.io.Serializable;
import java.util.List;

import gnj_soft.salsa.club.dance.model.Member;

/**
 * This class is the data access object for {@link Member}
 * 
 * @author Ghislain N.
 */
public interface MemberDao {
	
	public List<Member> getMembers() throws Exception;
	
	public Member getMemberByMemberId(Long memberId) throws Exception;
	
	public Member getMemberByLastName(String lastName, String firstName) throws Exception;
	
	public List<Member> getMembersByLevel(Integer level) throws Exception;
	
	public boolean isMemberExist(Long memberId) throws Exception;
	
	public long countExistingMembers() throws Exception;
	
	public Serializable saveMember(Member member) throws Exception;
	
	public void saveOrUpdateMember(Member member) throws Exception;
	
	public void deleteMemberById(Long id) throws Exception;
}

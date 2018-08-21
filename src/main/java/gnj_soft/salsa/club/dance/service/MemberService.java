package gnj_soft.salsa.club.dance.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gnj_soft.salsa.club.dance.dao.MemberDao;
import gnj_soft.salsa.club.dance.model.Member;

/**
 * This class provides services according to salsa-club's members
 * 
 * @author gnj_soft
 */
@Service
public class MemberService {
	
	public final static Logger logger = Logger.getLogger(MemberService.class);

	@Autowired
	private MemberDao memberDao;
	
	public Optional<List<Member>> getMembers() {
		List<Member> members = null;
		try {
			members = this.memberDao.getMembers();
		} catch (Exception e) {
			logger.error("Error when getting members list ", e);
		}
		return Optional.ofNullable(members);
	}

	public Optional<Member> getMemberByMemberId(Long memberId) {
		Member member = null;
		try {
			member = this.memberDao.getMemberByMemberId(memberId);
		} catch (Exception e) {
			logger.error("Error when getting member with id : " + memberId, e);
		}
		return Optional.ofNullable(member);
	}

	public Optional<Member> getMemberByLastName(String lastName, String firstName) {
		Member member = null;
		try {
			member = this.memberDao.getMemberByLastName(lastName, firstName);
		} catch (Exception e) {
			logger.error("Error when getting member with last name : " + lastName, e);
		}
		return Optional.ofNullable(member);
	}

	public Optional<List<Member>> getMembersByLevel(Integer level) {
		List<Member> members = null;
		try {
			members = this.memberDao.getMembersByLevel(level);
		} catch (Exception e) {
			logger.error("Error when getting members with level : " + level, e);
		}
		return Optional.ofNullable(members);
	}

	public Optional<Boolean> isMemberExist(Long memberId) {
		boolean isMemberExist = false;
		try {
			isMemberExist = this.memberDao.isMemberExist(memberId);
		} catch (Exception e) {
			logger.error("Error when checking existing member with id : " + memberId, e);
		}
		return Optional.of(isMemberExist);
	}

	public OptionalLong countExistingMembers() {
		long numberOfMember = 0;
		try {
			numberOfMember = this.memberDao.countExistingMembers();
		} catch (Exception e) {
			logger.error("Error when getting members number ", e);
		}
		return OptionalLong.of(numberOfMember);
	}

	public void saveMember(Member member) {
		try {
			this.memberDao.saveMember(member);
		} catch (Exception e) {
			logger.error("Error when saving member ", e);
		}
	}
	
	public void updateMember(Member member) {
		try {
			this.memberDao.saveOrUpdateMember(member);;
		} catch (Exception e) {
			logger.error("Error when updating member ", e);
		}
	}

	public void deleteMemberById(Long id) {
		try {
			this.memberDao.deleteMemberById(id);;
		} catch (Exception e) {
			logger.error("Error when deleting member with id : " + id, e);
		}
	}
}

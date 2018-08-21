package gnj_soft.salsa.club.dance.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gnj_soft.salsa.club.dance.dao.MemberDao;
import gnj_soft.salsa.club.dance.model.Member;

/**
 * This is Hibernate's implementation class for {@link MemberDao}
 * @author gnj_soft
 */
@Repository
public class MemberDaoImp implements MemberDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Member getMemberByMemberId(Long memberId) {
		return this.sessionFactory.getCurrentSession().load(Member.class, memberId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Member getMemberByLastName(String lastName, String firstName) {
		List<Member> members = this.sessionFactory.getCurrentSession().createQuery("from Member where lastName = ?").setParameter(0, lastName).list();
		Member member = members.get(0);
		if (members.size() > 0) {
			for(Member m : members)
				if(firstName.equals(m.getFirstName())) {
					member = m;
				}
		}
		return member;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMembersByLevel(Integer level) {
		return this.sessionFactory.getCurrentSession().createQuery("from Member where level = ?").setParameter(0, level).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMembers() {
		return this.sessionFactory.getCurrentSession().createQuery("from Member").list();
	}

	@Override
	public boolean isMemberExist(Long memberId) {
		return null != this.sessionFactory.getCurrentSession().get(Member.class, memberId);
	}

	@Override
	public long countExistingMembers() {
		return this.getMembers().size();
	}

	@Override
	public Serializable saveMember(Member member) {
		return this.sessionFactory.getCurrentSession().save(member);
	}

	@Override
	public void saveOrUpdateMember(Member member) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(member);
	}

	@Override
	public void deleteMemberById(Long id) {
		this.sessionFactory.getCurrentSession().delete(this.getMemberByMemberId(id));
		this.sessionFactory.getCurrentSession().flush();
	}
}

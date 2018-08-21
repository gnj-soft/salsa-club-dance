package gnj_soft.salsa.club.dance.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gnj_soft.salsa.club.dance.dao.PlaningDao;
import gnj_soft.salsa.club.dance.model.Planing;

/**
 * This is Hibernate's implementation class for {@link PlaningDao}
 * @author gnj_soft
 */
@Repository
public class PlaningDoaImp implements PlaningDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Planing getPlaningByPlaningId(Long planingId) {
		return this.sessionFactory.getCurrentSession().load(Planing.class, planingId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Planing> getPlaningsByLessonId(Long lessonId) {
		return this.sessionFactory.getCurrentSession().createQuery("from Planing where lessonId = ?").setParameter(0, lessonId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Planing> getPlaningsByTeacherId(Long teacherId) {
		return this.sessionFactory.getCurrentSession().createQuery("from Planing where teacherId = ?").setParameter(0, teacherId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Planing> getPlaningsByMemberId(Long memberId) {
		return this.sessionFactory.getCurrentSession().createQuery("from Planing where memberId = ?").setParameter(0, memberId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Planing> getPlanings() {
		return this.sessionFactory.getCurrentSession().createQuery("from Planing").list();
	}

	@Override
	public boolean isPlaningExist(Long planingId) {
		return null != this.sessionFactory.getCurrentSession().get(Planing.class, planingId);
	}

	@Override
	public long countExistingPlanings() {
		return this.getPlanings().size();
	}

	@Override
	public Serializable savePlaning(Planing planing) {
		return this.sessionFactory.getCurrentSession().save(planing);
	}

	@Override
	public void saveOrUpdatePlaning(Planing planing) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(planing);
	}

	@Override
	public void deletePlaning(Long id) {
		this.sessionFactory.getCurrentSession().delete(this.getPlaningByPlaningId(id));
		this.sessionFactory.getCurrentSession().flush();
	}
}

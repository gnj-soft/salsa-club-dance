package gnj_soft.salsa.club.dance.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gnj_soft.salsa.club.dance.dao.LessonDao;
import gnj_soft.salsa.club.dance.model.Lesson;

/**
 * This is Hibernate's implementation class for {@link LessonDao}
 * @author gnj_soft
 */
@Repository
public class LessonDaoImp implements LessonDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Lesson getLessonByLessonId(Long lessonId) {
		return this.sessionFactory.getCurrentSession().load(Lesson.class, lessonId);
	}

	@SuppressWarnings("unchecked")
	public Lesson getLessonByLessonName(String lessonName) {	
		List<Lesson> result = this.sessionFactory.getCurrentSession().createQuery("from Lesson where lessonName = ?")
		.setParameter(0, lessonName).list();
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getLessonsByLessonLevel(Integer lessonLevel) {
		return this.sessionFactory.getCurrentSession().createQuery("from Lesson where lessonLevel = ?")
				.setParameter(0, lessonLevel)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Lesson> getLessons() {	
		return this.sessionFactory.getCurrentSession().createQuery("from Lesson").list();
	}

	public Serializable saveLesson(Lesson lesson) {
		return this.sessionFactory.getCurrentSession().save(lesson);
	}
	
	@Override
	public void saveOrUpdateLesson(Lesson lesson) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(lesson);
	}

	public void deleteLesson(Long id) {
		this.sessionFactory.getCurrentSession().delete(this.getLessonByLessonId(id));
		this.sessionFactory.getCurrentSession().flush();
	}

	public long countExistingLessons() {
		return this.getLessons().size();
	}

	public boolean isLessonExist(Long lessonId) {
		return null != this.sessionFactory.getCurrentSession().get(Lesson.class, lessonId);
	}
}

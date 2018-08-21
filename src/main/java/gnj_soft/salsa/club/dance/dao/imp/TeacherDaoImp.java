package gnj_soft.salsa.club.dance.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gnj_soft.salsa.club.dance.dao.LessonDao;
import gnj_soft.salsa.club.dance.dao.TeacherDao;
import gnj_soft.salsa.club.dance.model.Teacher;

/**
 * This is Hibernate's implementation class for {@link LessonDao}
 * @author gnj_soft
 */
@Repository
public class TeacherDaoImp implements TeacherDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> getTeachers() throws Exception {
		return this.sessionFactory.getCurrentSession().createQuery("from Teacher").list();
	}

	@Override
	public Teacher getTeacherByTeacherId(Long teacherId) throws Exception {
		return this.sessionFactory.getCurrentSession().load(Teacher.class, teacherId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Teacher getTeacherByLastName(String lastName, String firstName) throws Exception {
		List<Teacher> teachers = this.sessionFactory.getCurrentSession().createQuery("from Teacher where lastName = ?").setParameter(0, lastName).list();
		Teacher teacher = teachers.get(0);
		if (teachers.size() > 0) {
			for(Teacher t : teachers)
				if(firstName.equals(t.getFirstName())) {
					teacher = t;
				}
		}
		return teacher;
	}

	@Override
	public boolean isTeacherExist(Long teacherId) throws Exception {
		return null != this.sessionFactory.getCurrentSession().get(Teacher.class, teacherId);
	}

	@Override
	public long countExistingTeachers() throws Exception {
		return this.getTeachers().size();
	}

	@Override
	public Serializable saveTeacher(Teacher teacher) throws Exception {
		return this.sessionFactory.getCurrentSession().save(teacher);
	}

	@Override
	public void saveOrUpdateTeacher(Teacher teacher) throws Exception {		
		this.sessionFactory.getCurrentSession().saveOrUpdate(teacher);
	}

	@Override
	public void deleteTeacherById(Long id) throws Exception {
		this.sessionFactory.getCurrentSession().delete(this.getTeacherByTeacherId(id));
		this.sessionFactory.getCurrentSession().flush();
	}
}

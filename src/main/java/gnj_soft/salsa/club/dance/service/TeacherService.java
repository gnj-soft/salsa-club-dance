package gnj_soft.salsa.club.dance.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gnj_soft.salsa.club.dance.dao.TeacherDao;
import gnj_soft.salsa.club.dance.model.Teacher;

/**
 * This class provides services according to salsa-club's teachers
 * 
 * @author gnj_soft
 */
@Service
public class TeacherService {

	@Autowired
	private TeacherDao teacherDao;
	
	public final static Logger logger = Logger.getLogger(TeacherService.class);
	
	public Optional<List<Teacher>> getTeachers() {
		List<Teacher> teachers = null;
		try {
			teachers = this.teacherDao.getTeachers();
		} catch (Exception e) {
			logger.error("Error when getting teachers list ", e);
		}
		return Optional.ofNullable(teachers);
	}

	public Optional<Teacher> getTeacherByTeacherId(Long teacherId) {
		Teacher teacher = null;
		try {
			teacher = this.teacherDao.getTeacherByTeacherId(teacherId);
		} catch (Exception e) {
			logger.error("Error when getting teacher with id : " + teacherId, e);
		}
		return Optional.ofNullable(teacher);
	}

	public Optional<Teacher> getTeacherByLastName(String lastName, String firstName) {
		Teacher teacher = null;
		try {
			teacher = this.teacherDao.getTeacherByLastName(lastName, firstName);
		} catch (Exception e) {
			logger.error("Error when getting teacher with last name : " + lastName, e);
		}
		return Optional.ofNullable(teacher);
	}

	public Optional<Boolean> isTeacherExist(Long teacherId) {
		boolean isTeacherExist = false;
		try {
			isTeacherExist = this.teacherDao.isTeacherExist(teacherId);
		} catch (Exception e) {
			logger.error("Error when checking existing teacher with id : " + teacherId, e);
		}
		return Optional.of(isTeacherExist);
	}

	public OptionalLong countExistingTeachers() {
		long numberOfTeacher = 0;
		try {
			numberOfTeacher = this.teacherDao.countExistingTeachers();
		} catch (Exception e) {
			logger.error("Error when getting teachers number ", e);
		}
		return OptionalLong.of(numberOfTeacher);
	}

	public void saveTeacher(Teacher teacher) {
		try {
			this.teacherDao.saveTeacher(teacher);
		} catch (Exception e) {
			logger.error("Error when saving teacher ", e);
		}
	}
	
	public void updateTeacher(Teacher teacher) {
		try {
			this.teacherDao.saveOrUpdateTeacher(teacher);;
		} catch (Exception e) {
			logger.error("Error when updating teacher ", e);
		}
	}

	public void deleteTeacherById(Long id) {
		try {
			this.teacherDao.deleteTeacherById(id);
		} catch (Exception e) {
			logger.error("Error when deleting teacher with id : " + id, e);
		}
	}
}

package gnj_soft.salsa.club.dance.dao;

import java.io.Serializable;
import java.util.List;

import gnj_soft.salsa.club.dance.model.Teacher;

/**
 * This class is the data access object for {@link Teacher}
 * 
 * @author Ghislain N.
 */
public interface TeacherDao {

	public List<Teacher> getTeachers() throws Exception;
	
	public Teacher getTeacherByTeacherId(Long teacherId) throws Exception;
	
	public Teacher getTeacherByLastName(String lastName, String firstName) throws Exception;
	
	public boolean isTeacherExist(Long teacherId) throws Exception;
	
	public long countExistingTeachers() throws Exception;
	
	public Serializable saveTeacher(Teacher teacher) throws Exception;
	
	public void saveOrUpdateTeacher(Teacher teacher) throws Exception;
	
	public void deleteTeacherById(Long id) throws Exception;
}

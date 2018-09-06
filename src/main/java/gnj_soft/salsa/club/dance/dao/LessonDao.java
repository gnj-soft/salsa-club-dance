package gnj_soft.salsa.club.dance.dao;

import java.io.Serializable;
import java.util.List;

import gnj_soft.salsa.club.dance.model.Lesson;

/**
 * This class is the data access object for {@link Lesson}
 * 
 * @author Ghislain N.
 */
public interface LessonDao {
	
	public Lesson getLessonByLessonId(Long lessonId) throws Exception;
	
	public Lesson getLessonByLessonName(String lessonName) throws Exception;
	
	public List<Lesson> getLessonsByLessonLevel(Integer lessonLevel) throws Exception;
	
	public List<Lesson> getLessons() throws Exception;
	
	public Serializable saveLesson(Lesson lesson) throws Exception;
	
	public void saveOrUpdateLesson(Lesson lesson) throws Exception;
	
	public void deleteLesson(Long id) throws Exception;
	
	public long countExistingLessons() throws Exception;
	
	public boolean isLessonExist(Long lessonId) throws Exception;
}

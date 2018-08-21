package gnj_soft.salsa.club.dance.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gnj_soft.salsa.club.dance.dao.LessonDao;
import gnj_soft.salsa.club.dance.model.Lesson;

/**
 * This class provides services according to salsa-club's lessons
 * 
 * @author gnj_soft
 */
@Service
public class LessonService {

	@Autowired
	private LessonDao lessonDao;

	public final static Logger logger = Logger.getLogger(LessonService.class);

	public Optional<Lesson> getLessonByLessonId(Long lessonId) {
		Lesson lesson = null;
		try {
			lesson = this.lessonDao.getLessonByLessonId(lessonId);
		} catch (Exception e) {
			logger.error("Error when getting lesson with id : " + lessonId, e);
		}
		return Optional.ofNullable(lesson);
	}

	public Optional<Lesson> getLessonByLessonName(String lessonName) {
		Lesson lesson = null;
		try {
			lesson = this.lessonDao.getLessonByLessonName(lessonName);
		} catch (Exception e) {
			logger.error("Error when getting lesson with Name : " + lessonName, e);
		}
		return Optional.ofNullable(lesson);
	}

	public Optional<List<Lesson>> getLessonsByLessonLevel(Integer lessonLevel) {
		List<Lesson> lessonList = null;
		try {
			lessonList = this.lessonDao.getLessonsByLessonLevel(lessonLevel);
		} catch (Exception e) {
			logger.error("Error when getting lesson list with level : " + lessonLevel, e);
		}
		return Optional.ofNullable(lessonList);
	}

	public Optional<Boolean> isLessonExist(Long lessonId) {
		boolean isLessonExist = false;
		try {
			isLessonExist = this.lessonDao.isLessonExist(lessonId);
		} catch (Exception e) {
			logger.error("Error when checking existing lesson with id : " + lessonId, e);
		}
		return Optional.of(isLessonExist);
	}

	public OptionalLong countExistingLessons() {
		long numberOfLesson = 0;
		try {
			numberOfLesson = this.lessonDao.countExistingLessons();
		} catch (Exception e) {
			logger.error("Error when getting lessons number ", e);
		}
		return OptionalLong.of(numberOfLesson);
	}

	public void deleteLessonById(Long id) {
		try {
			this.lessonDao.deleteLesson(id);
		} catch (Exception e) {
			logger.error("Error when deleting lesson with id : " + id, e);
		}
	}

	public void saveLesson(Lesson lesson) {
		try {
			this.lessonDao.saveLesson(lesson);
		} catch (Exception e) {
			logger.error("Error when saving lesson ", e);
		}
	}
	
	public void updateLesson(Lesson lesson) {
		try {
			this.lessonDao.saveOrUpdateLesson(lesson);
		} catch (Exception e) {
			logger.error("Error when updating lesson ", e);
		}
	}

	public Optional<List<Lesson>> getLessons() {
		List<Lesson> lessonList = null;
		try {
			lessonList = this.lessonDao.getLessons();
		} catch (Exception e) {
			logger.error("Error when getting lessons list ", e);
		}
		return Optional.ofNullable(lessonList);
	}
}

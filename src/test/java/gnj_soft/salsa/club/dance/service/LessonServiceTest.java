package gnj_soft.salsa.club.dance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import gnj_soft.salsa.club.dance.AppTestConfig;
import gnj_soft.salsa.club.dance.model.Lesson;


/**
 * Test class for {@link LessonService}
 * @author gnj_soft
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfig.class)
@Transactional
public class LessonServiceTest {

	@Autowired
	private LessonService lessonService;


	@Test
	public void saveLesson() {
		Assert.assertTrue(this.lessonService.getLessons().isPresent());
	}
	
	@Test
	public void getLessons() {
		
		Optional<List<Lesson>> optionalLessons = this.lessonService.getLessons();
		
		Assert.assertTrue(optionalLessons.isPresent());
		Assert.assertEquals(10, optionalLessons.get().size());
	}
	
	@Test
	public void getLessonByLessonId() {
		
		String lessonName = "Sombrero";
		Optional<Lesson> optionalLesson = this.lessonService.getLessonByLessonId(this.getLessonIdForGivingLessonName(lessonName));
		
		Assert.assertTrue(optionalLesson.isPresent());
		Lesson lesson = optionalLesson.get();
		Assert.assertTrue(lessonName.equals(lesson.getLessonName()));
	}
	
	@Test
	public void getLessonByLessonName() {
		
		String lessonName = "Ochenta Y quatro";
		Optional<Lesson> optionalLesson = this.lessonService.getLessonByLessonName(lessonName);
		
		Assert.assertTrue(optionalLesson.isPresent());
		Lesson lesson = optionalLesson.get();
		Assert.assertTrue(lessonName.equals(lesson.getLessonName()));
		Assert.assertEquals(5, lesson.getDuration().longValue());
	}
	
	@Test
	public void getLessonByLevel() {
		
		Integer lessonLevel = 4;
		Optional<List<Lesson>> optionalLessons = this.lessonService.getLessonsByLessonLevel(lessonLevel);
		
		Assert.assertTrue(optionalLessons.isPresent());
		List<Lesson> lessons = optionalLessons.get();
		lessons.forEach(lesson -> Assert.assertEquals(lessonLevel, lesson.getLessonLevel()));
		Assert.assertEquals(3, lessons.size());
	}
	
	@Test
	public void isLessonExist() {
		
		String lessonName = "Registrala";
		Optional<Boolean> optionalBoolean = this.lessonService.isLessonExist(this.getLessonIdForGivingLessonName(lessonName));
		Assert.assertTrue(optionalBoolean.isPresent());
		Assert.assertTrue(optionalBoolean.get());
	}
	
	@Test
	public void countExistingLessons() {
		
		OptionalLong optionalLong = this.lessonService.countExistingLessons();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(10, optionalLong.getAsLong());
	}
	
	@Test
	public void deleteLessonById() {
		
		String lessonName = "Lesson To Delete";
		
		Lesson lessonToDelete = new Lesson(null, lessonName, 1, new Date(), new Date(), 3);
		this.lessonService.saveLesson(lessonToDelete);
		
		OptionalLong optionalLong = this.lessonService.countExistingLessons();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(11, optionalLong.getAsLong());
		
		Long lessonId = this.getLessonIdForGivingLessonName(lessonName);
		
		this.lessonService.deleteLessonById(lessonId);
		
		Optional<Boolean> optionalBoolean = this.lessonService.isLessonExist(lessonId);
		Assert.assertTrue(optionalBoolean.isPresent());
		Assert.assertFalse(optionalBoolean.get());
		
		optionalLong = this.lessonService.countExistingLessons();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(10, optionalLong.getAsLong());
	}
	
	@Test
	public void updateLesson() {
		
		String lessonNameToUpadate = "Setenta";
		Integer newLessonLevel = 8;
		Optional<Lesson> optionalLesson = this.lessonService.getLessonByLessonName(lessonNameToUpadate);
		Assert.assertTrue(optionalLesson.isPresent());
		Lesson lesson = optionalLesson.get();
		lesson.setLessonLevel(newLessonLevel);
		this.lessonService.updateLesson(lesson);
		
		optionalLesson = this.lessonService.getLessonByLessonName(lessonNameToUpadate);
		Assert.assertTrue(optionalLesson.isPresent());
		lesson = optionalLesson.get();
		Assert.assertEquals(newLessonLevel, lesson.getLessonLevel());		
	}

	@Before
	public void saveTestData() {

		List<Lesson> lessons = new ArrayList<Lesson>();
		Lesson sombrero = new Lesson(null, "Sombrero", 1, new Date(), new Date(), 3);
		lessons.add(sombrero);
		Lesson setenta = new Lesson(null, "Setenta", 2, new Date(), new Date(), 4);
		lessons.add(setenta);
		Lesson ochentaYQuatro = new Lesson(null, "Ochenta Y quatro", 3, new Date(), new Date(), 5);
		lessons.add(ochentaYQuatro);
		Lesson pirouli = new Lesson(null, "Pirouli", 3, new Date(), new Date(), 4);
		lessons.add(pirouli);
		Lesson sieteLoco = new Lesson(null, "Siete Loco", 3, new Date(), new Date(), 5);
		lessons.add(sieteLoco);
		Lesson montagna = new Lesson(null, "Montagna", 2, new Date(), new Date(), 5);
		lessons.add(montagna);
		Lesson registrala = new Lesson(null, "Registrala", 4, new Date(), new Date(), 2);
		lessons.add(registrala);
		Lesson copeliaComplicada = new Lesson(null, "Copelia Complicada", 4, new Date(), new Date(), 3);
		lessons.add(copeliaComplicada);
		Lesson cunada = new Lesson(null, "Cunada", 4, new Date(), new Date(), 5);
		lessons.add(cunada);
		Lesson vacuna = new Lesson(null, "Vacuna", 5, new Date(), new Date(), 4);
		lessons.add(vacuna);

		lessons.forEach(lesson -> this.lessonService.saveLesson(lesson));
	}
	
	public Long getLessonIdForGivingLessonName(String lessonName) {
		Optional<Lesson> optionalLesson = this.lessonService.getLessonByLessonName(lessonName);
		Assert.assertTrue(optionalLesson.isPresent());
		return optionalLesson.get().getLessonId();
	}
}

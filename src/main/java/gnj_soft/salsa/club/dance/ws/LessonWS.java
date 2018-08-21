package gnj_soft.salsa.club.dance.ws;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gnj_soft.salsa.club.dance.model.Lesson;
import gnj_soft.salsa.club.dance.service.LessonService;

/**
 * This class provides web services for salsa-club lessons
 * 
 * @author gnj_soft
 */
@RestController
@RequestMapping("lessons")
public class LessonWS {
	
	@Autowired
	private LessonService lessonService;
	
	@GetMapping("")
	public List<Lesson> getLessons() {
		List<Lesson> lessons = new ArrayList<>();
		Optional<List<Lesson>> optionalLessons = this.lessonService.getLessons();
		if (optionalLessons.isPresent()) {
			lessons = optionalLessons.get().stream()
					.sorted(Comparator.comparing(Lesson::getLessonLevel).thenComparing(Lesson::getLessonName))
					.collect(Collectors.toList());
		}
		return lessons;
	}

	@GetMapping("{id}")
	public Lesson getSalsaLessonById(@PathVariable Long id) {
		Lesson lesson = null;
		Optional<Lesson> optionalLesson = this.lessonService.getLessonByLessonId(id);
		if (optionalLesson.isPresent()) {
			lesson = optionalLesson.get();
		}
		return lesson;
	}
	
	@PostMapping("")
	public void addSalsaLesson(@RequestBody Lesson lesson) {
		this.lessonService.saveLesson(lesson);
	}

	@PutMapping("")
	public Lesson updateSalsaLesson(@RequestBody Lesson lesson) {
		this.lessonService.updateLesson(lesson);
		return lesson;
	}

	@DeleteMapping("{id}")
	public void deleteSalsaLesson(@PathVariable Long id) {
		this.lessonService.deleteLessonById(id);
	}
}

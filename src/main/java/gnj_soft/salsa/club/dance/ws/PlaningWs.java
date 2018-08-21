package gnj_soft.salsa.club.dance.ws;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
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

import gnj_soft.salsa.club.dance.model.Planing;
import gnj_soft.salsa.club.dance.model.Lesson;
import gnj_soft.salsa.club.dance.model.Teacher;
import gnj_soft.salsa.club.dance.service.PlaningService;
import gnj_soft.salsa.club.dance.service.LessonService;
import gnj_soft.salsa.club.dance.service.TeacherService;

/**
 * This class provides web services for salsa-club planings
 * @author gnj_soft
 */
@RestController
@RequestMapping("planings")
public class PlaningWs {
	
	@Autowired
	private PlaningService planingService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("")
	public List<Planing> getPlanings() {
		List<Planing> planings = new ArrayList<>();
		Optional<List<Planing>> optionalPlanings = this.planingService.getPlanings();
		if (optionalPlanings.isPresent()) {
			planings = optionalPlanings.get();
			Consumer<Planing> completePlaning = p -> {

				Optional<Lesson> optionalLesson = this.lessonService.getLessonByLessonId(p.getLessonId());
				optionalLesson.ifPresent(l -> p.setLessonName(l.getLessonName()));

				Optional<Teacher> optionalTeacher = this.teacherService.getTeacherByTeacherId(p.getTeacherId());
				optionalTeacher.ifPresent(t -> {
					p.setTeacherFirstName(t.getFirstName());
					p.setTeacherLastName(t.getLastName());
				});
			};
			planings.forEach(completePlaning);
			planings = planings.stream().sorted(Comparator.comparing(Planing::getTeacherLastName).reversed())
					.collect(Collectors.toList());			
		}
		return planings;
	}
	
	@GetMapping("{id}")
	public Planing getPlaningById(@PathVariable Long id) {
		Planing planing = null;
		Optional<Planing> optionalPlaning = this.planingService.getPlaningByPlaningId(id);
		if (optionalPlaning.isPresent()) {
			planing = optionalPlaning.get();
		}
		return planing;
	}
	
	@PostMapping("")
	public void addPlaning(@RequestBody Planing planing) {
		this.planingService.savePlaning(planing);
	}

	@PutMapping("")
	public Planing updatePlaning(@RequestBody Planing planing) {
		this.planingService.updatePlaning(planing);
		return planing;
	}

	@DeleteMapping("{id}")
	public void deletePlaning(@PathVariable Long id) {
		this.planingService.deletePlaningById(id);
	}
}

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

import gnj_soft.salsa.club.dance.model.Teacher;
import gnj_soft.salsa.club.dance.service.TeacherService;

/**
 * This class provides web services for salsa-club teachers
 * 
 * @author gnj_soft
 */
@RestController
@RequestMapping("teachers")
public class TeacherWs {
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("")
	public List<Teacher> getTeachers() {
		List<Teacher> teachers = new ArrayList<>();
		Optional<List<Teacher>> optionalTeachers = this.teacherService.getTeachers();
		if (optionalTeachers.isPresent()) {
			teachers = optionalTeachers.get().stream()
					.sorted(Comparator.comparing(Teacher::getLastName).thenComparing(Teacher::getFirstName))
					.collect(Collectors.toList());
		}
		return teachers;
	}

	@GetMapping("{id}")
	public Teacher getTeacherById(@PathVariable Long id) {
		Teacher teacher = null;
		Optional<Teacher> optionalTeacher = this.teacherService.getTeacherByTeacherId(id);
		if (optionalTeacher.isPresent()) {
			teacher = optionalTeacher.get();
		}
		return teacher;
	}
	
	@PostMapping("")
	public void addTeacher(@RequestBody Teacher teacher) {
		this.teacherService.saveTeacher(teacher);
	}

	@PutMapping("")
	public Teacher updateTeacher(@RequestBody Teacher teacher) {
		this.teacherService.updateTeacher(teacher);
		return teacher;
	}

	@DeleteMapping("{id}")
	public void deleteTeacher(@PathVariable Long id) {
		this.teacherService.deleteTeacherById(id);
	}
}

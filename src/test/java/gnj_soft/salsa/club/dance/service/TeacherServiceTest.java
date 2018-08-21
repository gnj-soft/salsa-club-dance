package gnj_soft.salsa.club.dance.service;

import java.util.ArrayList;
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
import gnj_soft.salsa.club.dance.model.Teacher;

/**
 * Test class for {@link TeacherService}
 * @author gnj_soft
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfig.class)
@Transactional
public class TeacherServiceTest {
	
	@Autowired
	private TeacherService teacherService;

	@Test
	public void saveTeacher() {
		Assert.assertTrue(this.teacherService.getTeachers().isPresent());
	}
	
	@Test
	public void getTeachers() {
		
		Optional<List<Teacher>> optionalTeachers = this.teacherService.getTeachers();
		
		Assert.assertTrue(optionalTeachers.isPresent());
		Assert.assertEquals(10, optionalTeachers.get().size());
	}
	
	@Test
	public void getTeacherByTeacherId() {
		
		String lastName = "QUIMER";
		String firstName = "Sergio";
		Optional<Teacher> optionalTeacher = this.teacherService.getTeacherByTeacherId(this.getTeacherIdForGivingLastName(lastName, ""));
		
		Assert.assertTrue(optionalTeacher.isPresent());
		Teacher teacher = optionalTeacher.get();
		Assert.assertTrue(lastName.equals(teacher.getLastName()) && firstName.equals(teacher.getFirstName()));
	}
	
	@Test
	public void getTeacherByLastName() {
		
		String lastName = "BARRIENTOS";
		String firstName = "Aliuska";
		Optional<Teacher> optionalTeacher = this.teacherService.getTeacherByLastName(lastName, firstName);
		
		Assert.assertTrue(optionalTeacher.isPresent());
		Teacher teacher = optionalTeacher.get();
		Assert.assertTrue(lastName.equals(teacher.getLastName()) && firstName.equals(teacher.getFirstName()));
	}
	
	@Test
	public void isTeacherExist() {
		
		String lastName = "MARTINEZ";
		String firstName = "Diaz";
		Optional<Boolean> optionalBoolean = this.teacherService.isTeacherExist(this.getTeacherIdForGivingLastName(lastName, firstName));
		Assert.assertTrue(optionalBoolean.isPresent());
		Assert.assertTrue(optionalBoolean.get());	
	}
	
	@Test
	public void countExistingTeachers() {
		
		OptionalLong optionalLong = this.teacherService.countExistingTeachers();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(10, optionalLong.getAsLong());
	}
	
	@Test
	public void updateTeacher() {
		
		String lastName = "SALSERITO";
		String firstName = "Alex";
		String newFirstName = "Alexito";
		Optional<Teacher> optionalTeacher = this.teacherService.getTeacherByLastName(lastName, firstName);
		Assert.assertTrue(optionalTeacher.isPresent());
		Teacher teacher = optionalTeacher.get();
		Assert.assertTrue(firstName.equals(teacher.getFirstName()));
		teacher.setFirstName(newFirstName);
		this.teacherService.updateTeacher(teacher);
		
		optionalTeacher = this.teacherService.getTeacherByLastName(lastName, newFirstName);
		Assert.assertTrue(optionalTeacher.isPresent());
		Assert.assertEquals(newFirstName, optionalTeacher.get().getFirstName());
	}
	
	@Test
	public void deleteTeacherById() {
		
		String lastName = "KANI";
		String firstName = "Oncle";
		Teacher oncle = new Teacher (null, firstName, lastName, "Semba, Semba, Semba");
		this.teacherService.saveTeacher(oncle);
		
		OptionalLong optionalLong = this.teacherService.countExistingTeachers();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(11, optionalLong.getAsLong());
		
		Long teacherId = this.getTeacherIdForGivingLastName(lastName, firstName);
		this.teacherService.deleteTeacherById(teacherId);
		
		Optional<Boolean> optionalBoolean = this.teacherService.isTeacherExist(teacherId);
		Assert.assertTrue(optionalBoolean.isPresent());
		Assert.assertFalse(optionalBoolean.get());
		
		optionalLong = this.teacherService.countExistingTeachers();
		Assert.assertTrue(optionalLong.isPresent());
		Assert.assertEquals(10, optionalLong.getAsLong());
	}
	
	@Before
	public void saveTestData() {
		
		List<Teacher> teachers = new ArrayList<Teacher>();
		Teacher sergio = new Teacher(null, "Sergio", "QUIMER", "Plus de 25 ans en tant que prof de Salsa");
		teachers.add(sergio);
		Teacher adel = new Teacher(null, "Adel", "MARTINEZ", "Total maîtrise du style Afro-cubain, mais se la pète un peu trop");
		teachers.add(adel);
		Teacher diaz = new Teacher(null, "Diaz", "MARTINEZ", "Spécialisée dans le lady style");
		teachers.add(diaz);
		Teacher ruddy = new Teacher(null, "Ruddy", "JAPPONT", "Kiz Kiz Kiz ... expérimenté en Kizomba");
		teachers.add(ruddy);
		Teacher aliuska = new Teacher(null, "Aliuska", "BARRIENTOS", "Rythmique rhumba afro cubain, lady style");
		teachers.add(aliuska);
		Teacher yordani = new Teacher(null, "Yordani", "LAZANO", "Style cubain confirmé");
		teachers.add(yordani);
		Teacher alex = new Teacher(null, "Alex", "SALSERITO", "Adore la Salsa Hip Hop et du Kompa");
		teachers.add(alex);
		Teacher ricardo = new Teacher(null, "Ricardo", "MORENASSO", "Meilleurs prof de Kizomba/Semba");
		teachers.add(ricardo);
		Teacher alyson = new Teacher(null, "Alyson", "CAP", "Passes de Kizomba facile à placer en soirée");
		teachers.add(alyson);
		Teacher ludo = new Teacher(null, "Ludo", "CAP JAPPONT", "Passes de Kizomba facile à placer en soirée");
		teachers.add(ludo);
		
		teachers.forEach(teacher -> this.teacherService.saveTeacher(teacher));
	}
	
	public Long getTeacherIdForGivingLastName(String lastName, String firstName) {
		Optional<Teacher> optionalTeacher = this.teacherService.getTeacherByLastName(lastName, firstName);
		Assert.assertTrue(optionalTeacher.isPresent());
		return optionalTeacher.get().getTeacherId();
	}
}

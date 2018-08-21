package gnj_soft.salsa.club.dance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import gnj_soft.salsa.club.dance.AppTestConfig;
import gnj_soft.salsa.club.dance.model.Planing;

/**
 * Test class for {@link PlaningService}
 * @author gnj_soft
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfig.class)
@Transactional
public class PlaningServiceTest {
	
	@Autowired
	private PlaningService planingService;
	
	@Test
	public void savePlaning() {
		Assert.assertTrue(this.planingService.getPlanings().isPresent());		
	}
	
	@Test
	public void getPlanings() {
		
		Optional<List<Planing>> optionalPlanings = this.planingService.getPlanings();
		
		Assert.assertTrue(optionalPlanings.isPresent());
		Assert.assertEquals(10, optionalPlanings.get().size());
	}
	
	public void getPlaningByPlaningId() {
		
		Long lessonId = 30l;
		Optional<List<Planing>> optionalLessons = this.planingService.getPlaningsByLessonId(lessonId);
	}
	
	public void getPlaningsByLessonId() {
		
	}
	
	public void getPlaningsByTeacherId() {
		
	}
	
	public void getPlaningsByMemberId() {
		
	}
	
	public void isPlaningExist() {
		
	}
	
	public void countExistingPlanings() {
		
	}
	
	public void updatePlaning() {
		
	}
	
	public void deletePlaningById() {
		
	}

	@Before
	public void saveTestData() {
		
		List<Planing> planings = new ArrayList<Planing>();
		Planing setenta = new Planing (null, 22l, 6l, 11l, "Setenta is one of the most important figure in salsa cubaine");
		planings.add(setenta);
		Planing cunada = new Planing (null, 29l, 7l, 9l, "Cunada is a level 4 lesson, it is for good danser");
		planings.add(cunada);
		Planing vacuna = new Planing (null, 30l, 9l, 7l, "Vacuna is in the level of Rhumba ... not every danser can get it");
		planings.add(vacuna);
		Planing copelia = new Planing (null, 28l, 11l, 5l, "Copelia Complicada could be as the start from level 3");
		planings.add(copelia);
		Planing montagna = new Planing (null, 26l, 13l, 3l, "Montagna can be put in right in the mittle for the beginner");
		planings.add(montagna);
		Planing sieteLoca = new Planing (null, 25l, 5l, 4l, "Siete Loco is a little bit difficult and belong to level 3");
		planings.add(sieteLoca);
		Planing ochentaYQuatro = new Planing (null, 23l, 8l, 6l, "Very beautiful, Ochenta Y Quatro belong to level 3");
		planings.add(ochentaYQuatro);
		Planing registrala = new Planing (null, 27l, 10l, 8l, "Elegant and nice when you get it, Registrala belong to level 3++");
		planings.add(registrala);
		Planing pirouli = new Planing (null, 24l, 24l, 10l, "Not easy to understand by girls, Pirouli is nice to see and belong to level 3");
		planings.add(pirouli);
		Planing sombrero = new Planing (null, 21l, 12l, 2l, "Sombrero is a hat ... first good figure in salsa cubain");
		planings.add(sombrero);
		
		planings.forEach(planing -> this.planingService.savePlaning(planing));
	}
}

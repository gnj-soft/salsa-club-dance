package gnj_soft.salsa.club.dance.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gnj_soft.salsa.club.dance.dao.PlaningDao;
import gnj_soft.salsa.club.dance.model.Planing;

/**
 * This class provides services according to salsa-club's planings
 * 
 * @author gnj_soft
 */
@Service
public class PlaningService {

	@Autowired
	private PlaningDao planingDao;
	
	public final static Logger logger = Logger.getLogger(PlaningService.class);
	
	public Optional<List<Planing>> getPlanings() {
		List<Planing> planings = null;
		try {
			planings =this.planingDao.getPlanings();
		} catch (Exception e) {
			logger.error("Error when getting planings list ", e);
		}
		return Optional.ofNullable(planings);
	}

	public Optional<Planing> getPlaningByPlaningId(Long planingId) {
		Planing planing = null;
		try {
			planing = this.planingDao.getPlaningByPlaningId(planingId);
		} catch (Exception e) {
			logger.error("Error when getting planing with id : " + planingId, e);
		}
		return Optional.ofNullable(planing);
	}

	public Optional<List<Planing>> getPlaningsByLessonId(Long lessonId) {
		List<Planing> planings = null;
		try {
			planings =this.planingDao.getPlaningsByLessonId(lessonId);
		} catch (Exception e) {
			logger.error("Error when getting planings with lesson id : " + lessonId, e);
		}
		return Optional.ofNullable(planings);
	}

	public Optional<List<Planing>> getPlaningsByTeacherId(Long teacherId) {
		List<Planing> planings = null;
		try {
			planings =this.planingDao.getPlaningsByTeacherId(teacherId);
		} catch (Exception e) {
			logger.error("Error when getting planings with teacher id : " + teacherId, e);
		}
		return Optional.ofNullable(planings);
	}

	public Optional<List<Planing>> getPlaningsByMemberId(Long memberId) {
		List<Planing> planings = null;
		try {
			planings =this.planingDao.getPlaningsByMemberId(memberId);
		} catch (Exception e) {
			logger.error("Error when getting planings with member id : " + memberId, e);
		}
		return Optional.ofNullable(planings);
	}

	public Optional<Boolean> isPlaningExist(Long planingId) {
		boolean isPlaningExist = false;
		try {
			isPlaningExist = this.planingDao.isPlaningExist(planingId);
		} catch (Exception e) {
			logger.error("Error when checking existing planing with id : " + planingId, e);
		}
		return Optional.of(isPlaningExist);
	}

	public OptionalLong countExistingPlanings() {
		long numberOfLesson = 0;
		try {
			numberOfLesson = this.planingDao.countExistingPlanings();
		} catch (Exception e) {
			logger.error("Error when getting planing number ", e);
		}
		return OptionalLong.of(numberOfLesson);
	}

	public void savePlaning(Long planingId, Long lessonId, Long teacherId, Long memberId, String note) {
		this.savePlaning(new Planing(planingId, lessonId, teacherId, memberId, note));
	}

	public void savePlaning(Planing planing) {
		try {
			this.planingDao.savePlaning(planing);
		} catch (Exception e) {
			logger.error("Error when saving planing ", e);
		}
	}
	
	public void updatePlaning(Planing planing) {
		try {
			this.planingDao.saveOrUpdatePlaning(planing);;
		} catch (Exception e) {
			logger.error("Error when updating planing ", e);
		}
	}

	public void deletePlaningById(Long id) {
		try {
			this.planingDao.deletePlaning(id);
		} catch (Exception e) {
			logger.error("Error when deleting planing with id : " + id, e);
		}
	}
}

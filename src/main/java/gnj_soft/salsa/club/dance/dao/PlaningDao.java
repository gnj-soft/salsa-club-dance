package gnj_soft.salsa.club.dance.dao;

import java.io.Serializable;
import java.util.List;

import gnj_soft.salsa.club.dance.model.Planing;

/**
 * This class is the data access object for {@link Planing}
 * 
 * @author Ghislain N.
 */
public interface PlaningDao {
	
	public List<Planing> getPlanings() throws Exception;

	public Planing getPlaningByPlaningId(Long planingId) throws Exception;
	
	public boolean isPlaningExist(Long planingId) throws Exception;
	
	public long countExistingPlanings() throws Exception;
	
	public Serializable savePlaning(Planing planing) throws Exception;
	
	public void saveOrUpdatePlaning(Planing planing) throws Exception;
	
	public void deletePlaning(Long id) throws Exception;
}

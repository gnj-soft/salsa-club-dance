package gnj_soft.salsa.club.dance.dao;

import java.io.Serializable;
import java.util.List;

import gnj_soft.salsa.club.dance.model.Login;

/**
 * This class is the data access object for {@link Login}
 * 
 * @author gnj_soft
 */
public interface LoginDao {
	
	public List<Login> getLogins() throws Exception;

	public Login getLoginById(Long id) throws Exception;

	public Login getLoginByUsername(String username) throws Exception;

	public Login getLoginByEmail(String email) throws Exception;
	
	public Serializable saveLogin(Login login) throws Exception;
	
	public void deleteLoginById(Long id) throws Exception;
	
	public boolean isLoginExist(Long id) throws Exception;
	
	public long countExistingLogin() throws Exception;
}

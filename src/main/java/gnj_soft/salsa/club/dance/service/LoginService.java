package gnj_soft.salsa.club.dance.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gnj_soft.salsa.club.dance.dao.LoginDao;
import gnj_soft.salsa.club.dance.model.Login;

/**
 * This class provides services according to salsa-club's logins
 * 
 * @author gnj_soft
 */
@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	public final static Logger logger = Logger.getLogger(LoginService.class);

	public Optional<List<Login>> getLogins() {
		List<Login> logins = null;
		try {
			logins = this.loginDao.getLogins();
		} catch (Exception e) {
			logger.error("Error when getting logins list ", e);
		}
		return Optional.ofNullable(logins);
	}

	public Optional<Login> getLoginById(Long id) {
		Login login = null;
		try {
			login = this.loginDao.getLoginById(id);
		} catch (Exception e) {
			logger.error("Error when getting login with id : " + id, e);
		}
		return Optional.ofNullable(login);
	}

	public Optional<Login> getLoginByUsername(String username) {
		Login login = null;
		try {
			login = this.loginDao.getLoginByUsername(username);
		} catch (Exception e) {
			logger.error("Error when getting login with username : " + username, e);
		}
		return Optional.ofNullable(login);
	}

	public Optional<Login> getLoginByEmail(String email) {
		Login login = null;
		try {
			login = this.loginDao.getLoginByEmail(email);
		} catch (Exception e) {
			logger.error("Error when getting login with email : " + email, e);
		}
		return Optional.ofNullable(login);
	}

	public void saveLogin(Login login) {
		try {
			this.loginDao.saveLogin(login);
		} catch (Exception e) {
			logger.error("Error when saving login ", e);
		}
	}

	public void deleteLoginById(Long id) {
		try {
			this.loginDao.deleteLoginById(id);
		} catch (Exception e) {
			logger.error("Error when deleting login with id : " + id, e);
		}
	}

	public Optional<Boolean> isLoginExist(Long id) {
		boolean isLoginExist = false;
		try {
			isLoginExist = this.loginDao.isLoginExist(id);
		} catch (Exception e) {
			logger.error("Error when checking existing login with id : " + id, e);
		}
		return Optional.of(isLoginExist);
	}

	public OptionalLong countExistingLogin() {
		long numberOfLogin = 0;
		try {
			numberOfLogin = this.loginDao.countExistingLogin();
		} catch (Exception e) {
			logger.error("Error when getting logins number ", e);
		}
		return OptionalLong.of(numberOfLogin);
	}
}

package gnj_soft.salsa.club.dance.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gnj_soft.salsa.club.dance.dao.LoginDao;
import gnj_soft.salsa.club.dance.model.Login;

/**
 * This is Hibernate's implementation class for {@link LoginDao}
 * @author gnj_soft
 */
@Repository
public class LoginDaoImp implements LoginDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Login getLoginById(Long id) {
		return this.sessionFactory.getCurrentSession().load(Login.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Login getLoginByUsername(String username) {
		List<Login> logins = this.sessionFactory.getCurrentSession().createQuery("from Login where username = ?").setParameter(0, username).list();
		return logins.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Login getLoginByEmail(String email) {
		List<Login> logins = this.sessionFactory.getCurrentSession().createQuery("from Login where email = ?").setParameter(0, email).list();
		return logins.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Login> getLogins() {
		return this.sessionFactory.getCurrentSession().createQuery("from Login").list();
	}

	@Override
	public Serializable saveLogin(Login login) {
		return this.sessionFactory.getCurrentSession().save(login);
	}

	@Override
	public void deleteLoginById(Long id) {
		this.sessionFactory.getCurrentSession().delete(this.getLoginById(id));
		this.sessionFactory.getCurrentSession().flush();
	}

	@Override
	public boolean isLoginExist(Long id) {
		return null != this.getLoginById(id);
	}

	@Override
	public long countExistingLogin() {
		return this.getLogins().size();
	}
}

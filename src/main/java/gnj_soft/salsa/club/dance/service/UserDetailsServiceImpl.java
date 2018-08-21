package gnj_soft.salsa.club.dance.service;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gnj_soft.salsa.club.dance.model.Login;
import gnj_soft.salsa.club.dance.service.LoginService;

/**
 * This class help for login use spring security
 * @author gnj_soft
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private LoginService loginService;

	public UserDetailsServiceImpl(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> optionlOfLogin = loginService.getLoginByUsername(username);
		if (!optionlOfLogin.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return new User(optionlOfLogin.get().getUsername(), optionlOfLogin.get().getPassword(), emptyList());
	}
}

package gnj_soft.salsa.club.dance.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gnj_soft.salsa.club.dance.model.Login;
import gnj_soft.salsa.club.dance.service.LoginService;

/**
 * This class provides web services for salsa-club login
 * 
 * @author gnj_soft
 */
@RestController
@RequestMapping("/logins")
public class LoginWs {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("")
	public List<Login> getLogins() {
		List<Login> logins = new ArrayList<>();
		Optional<List<Login>> optionalLogins = this.loginService.getLogins();
		if (optionalLogins.isPresent()) {
			logins = optionalLogins.get();
		}
		return logins;
	}

	@GetMapping("{username}")
	public Login getLoginByUsername(@PathVariable String username) {
		Login login = null;
		Optional<Login> optionalLogin = this.loginService.getLoginByUsername(username);
		if (optionalLogin.isPresent()) {
			login = optionalLogin.get();
		}
		return login;
	}

	@PostMapping("")
	public void addLogin(@RequestBody Login login) {
		login.setPassword(this.bCryptPasswordEncoder.encode(login.getPassword()));
		this.loginService.saveLogin(login);
	}
}

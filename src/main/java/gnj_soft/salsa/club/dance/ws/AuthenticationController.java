package gnj_soft.salsa.club.dance.ws;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gnj_soft.salsa.club.dance.config.JwtTokenUtil;
import gnj_soft.salsa.club.dance.model.AuthToken;
import gnj_soft.salsa.club.dance.model.Login;
import gnj_soft.salsa.club.dance.model.LoginUser;
import gnj_soft.salsa.club.dance.service.LoginService;

/**
 * This class is used for user identification and token generation
 * 
 * @author gnj_soft
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

		String token = null;
		final Optional<Login> optionalLogin = loginService.getLoginByUsername(loginUser.getUsername());
		if (optionalLogin.isPresent()) {
			token = JwtTokenUtil.generateToken(optionalLogin.get());
		}	
		return ResponseEntity.ok(new AuthToken(token));
	}
}

package gnj_soft.salsa.club.dance.config;

import java.io.Serializable;
import java.util.Date;

import gnj_soft.salsa.club.dance.security.SecurityConstants;
import gnj_soft.salsa.club.dance.model.Login;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable{

	public static String generateToken(Login login) {
		return Jwts.builder().setSubject(login.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET.getBytes()).compact();

	}

}

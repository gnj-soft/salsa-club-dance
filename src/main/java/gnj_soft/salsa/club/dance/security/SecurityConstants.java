package gnj_soft.salsa.club.dance.security;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
	// public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final long EXPIRATION_TIME = 007_200_000; // 2 hours
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	// public static final String LOGINS_URL = "/logins";
	public static final String TOKEN_GENERATE_URL = "/token/generate-token";
}

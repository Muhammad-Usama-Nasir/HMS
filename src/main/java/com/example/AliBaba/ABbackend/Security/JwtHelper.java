package com.example.AliBaba.ABbackend.Security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

	//Time for the valid token
	public static final long JWT_TOKEN_VALIDILITY = 5 * 60 * 60;
	
	//secret key
	private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";
	
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
	    SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDILITY * 1000))
                .signWith(key, SignatureAlgorithm.HS256) // Use SecretKey object
                .compact();
	}
	
	//generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
	
	// check if the token is expired
	private Boolean isTokenExpired(String token) {
		final Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	//gets username from token
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
	
	//Generic function to apply all of the upper claims
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
	
	// retrieving information from the token using secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder()
	               .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())) //hmacShaKeyFor is used to create the signing key from the secret
	               .build()
	               .parseClaimsJws(token)
	               .getBody();
		

	}
	
	//validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	
	
	
	
	
	
}

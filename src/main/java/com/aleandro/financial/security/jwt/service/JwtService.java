package com.aleandro.financial.security.jwt.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.aleandro.financial.UserSec.infra.models.UserSecModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${spring.security.jwt.secret-key}")
	private  String key = "";
	@Value("${spring.security.jwt.exp-time}")
	private Long exp_token_in_miliseconds = 3600000L;
		
	
	public JwtService() {

	}


	



	private Key getSignInKey() {
		byte[] key_bytes = Decoders.BASE64.decode(key);
		return Keys.hmacShaKeyFor(key_bytes);
	} 
	
	
	public String generateToken(Map<String, Object> claims,
			UserSecModel user) {
		Date now = new Date();
		Date exp = new Date(now.getTime() + exp_token_in_miliseconds);
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUser_name())
				.setIssuedAt(now)
				.setExpiration(exp)
				.setIssuer("pc_ale")
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateToken(UserSecModel user) {
		return generateToken(new HashMap<>(), user);
	}
	
	public boolean isTokenValid(String token, UserSecModel user){
		final String username = this.extractUserName(token);
		return username.equals(user.getUser_name()) &&
				!isTokenExpired(token);
	}
	
	
	private boolean isTokenExpired(String token) {
		Date token_expiration = this.extractClaim(token, Claims::getExpiration);
		if(new Date().after(token_expiration)){
			return true;
			}
		else {return false;}
		
	}






	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
		
	}
	
	public <T> T extractClaim(String token , Function<Claims, T> claim_solver){
		final Claims claims = this.extractAllClaims(token);
		return claim_solver.apply(claims);
		
		}
	
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		}


	
	

}
